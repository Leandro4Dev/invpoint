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
        JFrame frame = new JFrame("Inventário - Adler Pelzer Group");
        int size = 1200;
        int minSize = 1000;
        float p = 0.60f;
        frame.setPreferredSize(new Dimension(size, Math.round(size * p)));
        frame.setMinimumSize(new Dimension(minSize, Math.round(minSize * p)));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container main = frame.getContentPane();
        main.setPreferredSize(new Dimension(size, Math.round(size * p)));

        JPanel home = new Home();

        Header header = new Header(frame.getWidth(), () -> {});

        main.setLayout(new GridBagLayout());

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
        main.add(home, gbc);

        frame.pack();
        frame.setVisible(true);

    }

}
