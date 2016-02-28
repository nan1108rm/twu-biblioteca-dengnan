package com.twu.biblioteca;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dengnan on 16/2/29.
 */
public class BookListPage{
    private JFrame frame;

    public BookListPage(){
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(new Color(191, 230, 240));
        JLabel welcomeLabel = new JLabel("Welcome to BIBLIOTECA!");
        welcomeLabel.setFont(new Font("SanSerif",Font.BOLD,30));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panelTitle.add(welcomeLabel);
        frame.getContentPane().add(BorderLayout.NORTH,panelTitle);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
