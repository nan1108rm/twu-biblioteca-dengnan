package com.twu.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaApp{
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    BibliotecaApp window = new BibliotecaApp();
                    window.frame.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public BibliotecaApp(){
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

        ImageIcon bookPic = new ImageIcon(getClass().getResource("bookPic.jpg"));

        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        JLabel picLeft = new JLabel();
        JLabel picRight = new JLabel();
        picLeft.setIcon(bookPic);
        picRight.setIcon(bookPic);
        panelLeft.add(BorderLayout.CENTER,picLeft);
        panelRight.add(BorderLayout.CENTER,picRight);
        frame.getContentPane().add(BorderLayout.WEST,panelLeft);
        frame.getContentPane().add(BorderLayout.EAST,panelRight);

        JPanel panelCenter = new JPanel();
        JButton enterBtn = new JButton("Enter");
        JButton quit = new JButton("Quit");
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.Y_AXIS));
        panelCenter.setBackground(new Color(202, 184, 238));
        panelCenter.add(enterBtn);
        panelCenter.add(quit);
        frame.getContentPane().add(BorderLayout.CENTER, panelCenter);

        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
