package com.totalize;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

import com.totalize.infra.Firebase;
import com.totalize.models.count.Count;
import com.totalize.models.count.CountDAO;
import com.totalize.models.user.User;
import com.totalize.models.user.UserDAO;
import com.totalize.views.Home;
import com.totalize.views.Settings;
import com.totalize.views.components.Header;
import com.totalize.views.utils.Style;

public class App {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Firebase.config();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {createAndShowGUI();}
        });
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Totalize");
        int size = 1350;
        float p = 0.60f;
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container main = frame.getContentPane();

        JPanel home = new Home();
        JPanel settings = new Settings();

        Header header = new Header(frame.getWidth(), () -> {
            System.out.println(home.isVisible());
            if (home.isVisible()) {
                home.setVisible(false);
                settings.setVisible(true);
                System.out.println("show Home");
            } else {
                settings.setVisible(false);
                home.setVisible(true);
                System.out.println("show Settings");
            }
        });

        main.setLayout(new GridBagLayout());
        main.setPreferredSize(new Dimension(size, Math.round(size * p)));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 25;
        main.add(header, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.ipady = 1000;
        gbc.weightx = 1;
        gbc.weighty = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        settings.setVisible(true);
        main.add(home, gbc);

        settings.setVisible(false);
        main.add(settings, gbc);

        frame.pack();
        frame.setVisible(true);

    }

}
