package com.invpoint.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;

import com.invpoint.views.components.Buttons.Button;
import com.invpoint.views.components.Buttons.ButtonType;
import com.invpoint.views.utils.Callback;
import com.invpoint.views.utils.Style;

public class Header extends JPanel {

    public Header(int containerWidth, Callback navigate) {
        setBackground(Style.Colors.BLACK);
//        setPreferredSize(new Dimension(containerWidth, 40));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(30, 30));
        buttonPanel.setMaximumSize(new Dimension(200, 30));

        JLabel headerLabel = new JLabel("Inventário", SwingConstants.CENTER);
        headerLabel.setForeground(Style.Colors.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Button productsButton = new Button("", 30, 30, ButtonType.Accent, MaterialDesignC.COG);
        productsButton.setFont(new Font("Arial", Font.BOLD, 14));

        Button homeButton = new Button("", 30, 30, ButtonType.Accent, MaterialDesignA.ARROW_LEFT);
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));

        productsButton.addActionListener(e -> {
            navigate.execute();
            buttonPanel.remove(productsButton);
            buttonPanel.add(homeButton);
            homeButton.setBackground(Style.Colors.INDIGO);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        homeButton.addActionListener(e -> {
            navigate.execute();
            buttonPanel.remove(homeButton);
            buttonPanel.add(productsButton);
            productsButton.setBackground(Style.Colors.INDIGO);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

//        buttonPanel.add(productsButton);

        add(Box.createRigidArea(new Dimension(120, 0)));
        add(Box.createHorizontalGlue());
        add(headerLabel, BorderLayout.CENTER);
        add(Box.createHorizontalGlue());
        add(buttonPanel);
        add(Box.createRigidArea(new Dimension(10, 0)));
    }

}
