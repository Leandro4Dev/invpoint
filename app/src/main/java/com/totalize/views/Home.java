package com.totalize.views;

import com.totalize.infra.Firebase;
import com.totalize.models.count.Count;
import com.totalize.models.count.CountDAO;
import com.totalize.views.components.CountTableModel;
import com.totalize.views.utils.Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;


public class Home extends JPanel {


    private final JPanel buttonsPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();
    private final Border border = new EmptyBorder(10, 10, 10, 10);

    private final CountTableModel countTableModel = new CountTableModel();
    private final JTable countTable = new JTable(countTableModel);

    public Home() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        buttonsPanel.setBorder(border);
        tablePanel.setBorder(border);

        try{
//            Thread.sleep(10000);
            List<Count> counts = CountDAO.getAll();
            countTableModel.setList(counts);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }


//        buttonsPanel.setBackground(Style.Colors.LIGHT_GRAY);
//        tablePanel.setBackground(Style.Colors.RED);

        JScrollPane scrollPaneTabela = new JScrollPane(countTable);
//        scrollPaneTabela.setPreferredSize(new Dimension(500, 500));

        countTable.setBackground(Color.WHITE);
        countTable.setForeground(Color.BLACK);
        countTable.setFont(new Font("Arial", Font.PLAIN, 14));
        countTable.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < countTable.getColumnCount(); i++) {
            countTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tablePanel.add(scrollPaneTabela);

        JLabel text = new JLabel("Hello, world!", SwingConstants.CENTER);
        buttonsPanel.add(text);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 25;
        add(buttonsPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.ipady = 1000;
        gbc.weightx = 1;
        gbc.weighty = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(tablePanel, gbc);

    }

}
