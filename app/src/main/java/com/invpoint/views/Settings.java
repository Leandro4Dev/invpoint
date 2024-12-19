package com.invpoint.views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Settings extends JPanel {

    private final JPanel qrCodePanel = new JPanel();
    private final JPanel infoPanel = new JPanel();

    public Settings(){
        Border border = new EmptyBorder(10, 10, 10, 10);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        qrCodePanel.setBorder(border);
        infoPanel.setBorder(border);

        qrCodePanel.setBackground(Color.LIGHT_GRAY);


        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(qrCodePanel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 0.5;
        gbc.gridy = 0;
        gbc.gridx = 1;
        add(infoPanel, gbc);

    }
}
