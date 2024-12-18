package com.totalize.views;

import com.totalize.models.count.Count;
import com.totalize.models.count.CountDAO;
import com.totalize.services.ReportService;
import com.totalize.views.components.Buttons.Button;
import com.totalize.views.components.Buttons.ButtonType;
import com.totalize.views.components.CountTableModel;
import com.totalize.views.utils.DataWorker;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignR;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


public class Home extends JPanel {


    private final JPanel buttonsPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();

    private final CountTableModel countTableModel = new CountTableModel();
    private final JTable countTable = new JTable(countTableModel);

    public Home() {

        Border border = new EmptyBorder(10, 10, 10, 10);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        buttonsPanel.setBorder(border);
        tablePanel.setBorder(border);

        createTablePanel();
        createButtonsPanel();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonsPanel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1;
        gbc.weighty = 0.9;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(tablePanel, gbc);

    }

    private void createButtonsPanel(){

        JLabel title = new JLabel("Lançamento das etiquetas");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel loading = new JLabel("");

        Button refresh = new Button("Atualizar", 90,35, ButtonType.Accent, MaterialDesignR.REFRESH);
        Button generateReport = new Button("Gerar Relatório", 130,35, ButtonType.Primary, MaterialDesignC.CONTENT_SAVE);

        Button resetDatabase = new Button("Limpar Banco de dados", 170,35, ButtonType.Emphasis, MaterialDesignD.DELETE);

        Home component = this;

        refresh.addActionListener(e -> {
            loading.setText("Carregando dados....");
            DataWorker.run(this::getCounts, () ->{
                JOptionPane.showMessageDialog(component, "Dados atualizados!");
                loading.setText("");
            });
        });

        generateReport.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Realmente deseja gerar o relatório?") > 0) {
                return;
            }
            loading.setText("Gerando relatório....");
            DataWorker.run(() -> {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy_HH-mm-ss"));
                ReportService.generate("Report Contagem Inventário - " + now);
            }, () ->{
                JOptionPane.showMessageDialog(component, "Relatório salvo na pasta 'Downloads'.");
                loading.setText("");
            });
        });

        resetDatabase.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Realmente deseja limpar o banco de dados?") > 0) {
                return;
            }
            String input = JOptionPane.showInputDialog(this, "Para continuar digite 'DELETE' no campo abaixo:", "");
            if(!Objects.equals(input, "DELETE")){
                JOptionPane.showMessageDialog(component, "O texto está incorreto!");
                return;
            }
            loading.setText("Limpando banco de dados....");
            DataWorker.run(() -> {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy_HH-mm-ss"));
                ReportService.generate("Backup Contagem Inventário - "  + now);
                CountDAO.clearCollection();
                getCounts();
            }, () ->{
                DataWorker.run(this::getCounts, () ->{
                    JOptionPane.showMessageDialog(component, "Banco de dados reiniciado! O backup foi salvo na pasta 'Downloads'");
                    loading.setText("");
                });
            });
        });

        buttonsPanel.add(title);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(loading);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(refresh);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(generateReport);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(resetDatabase);
    }

    private void getCounts(){
        try{
            List<Count> counts = CountDAO.getAll();
            countTableModel.setList(counts);
        }catch(RuntimeException e){
            String msg = "Falha ao obter os dados!! \n Motivo: " + e.getMessage();
            JOptionPane.showMessageDialog(this, msg);
            System.out.println(msg);
        }
    }

    private void createTablePanel(){
        getCounts();
        JScrollPane scrollPaneTabela = new JScrollPane(countTable);

        countTable.setBackground(Color.WHITE);
        countTable.setForeground(Color.BLACK);
        countTable.setFont(new Font("Arial", Font.PLAIN, 14));
        countTable.setFillsViewportHeight(true);

        countTable.setRowHeight(20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < countTable.getColumnCount(); i++) {
            var column = countTable.getColumnModel().getColumn(i);
            column.setCellRenderer(centerRenderer);
        }

        tablePanel.add(scrollPaneTabela);
    }

}
