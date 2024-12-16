package com.totalize.views.components;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;


import javax.swing.table.AbstractTableModel;

import com.totalize.models.count.Count;

public class CountTableModel extends AbstractTableModel {

    private List<Count> counts;
    private final String[] columns = { "Data/Hora", "Usuário", "Matricula", "Serial", "Seq.", "Quantidade" };

    public CountTableModel() {
        counts = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return counts.size(); // Quantidade de linhas
    }

    @Override
    public int getColumnCount() {
        return columns.length; // Quantidade de colunas
    }

    @Override
    public String getColumnName(int column) {
        return columns[column]; // Nome das colunas
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Não permite edição das células
    }

    public void addItem(Count count) {
        counts.add(count);
        fireTableRowsInserted(0, counts.size() - 1);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < counts.size()) {
            counts.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    public void clear() {
        counts.clear();
        fireTableDataChanged();
    }

    public List<Count> getList() {
        return counts;
    }

    public void setList(List<Count> counts) {
        this.counts = counts;
        fireTableDataChanged();
    }

    public Count getItem(int index) {
        return counts.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Count count = counts.get(rowIndex);

        Date currentDate = count.getCreatedTime().toDate();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        return switch (columnIndex) {
            case 0 -> dateFormatter.format(currentDate);
            case 1 -> count.getUserData().getDisplay_name();
            case 2 -> count.getUserData().getRegistration();
            case 3 -> count.getSeq();
            case 4 -> count.getCount();
            case 5 -> count.getAmount();
            default -> null;
        };
    }

}
