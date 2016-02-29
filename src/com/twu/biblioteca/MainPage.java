package com.twu.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Created by dengnan on 16/2/29.
 */
public class MainPage implements ActionListener{
    private JFrame frame;
    final static ArrayList<Book> currentBookList = BibliotecaHandler.initBookList();
    static Object[][] bookArray= BibliotecaHandler.generateTableData(currentBookList);
    String[] columnNames = {"Book Name","Author","Published Year"};
    static ArrayList<Book> targetBookList = new ArrayList<Book>();

    public MainPage(){
        initialize();
    }

    private void initialize(){

        frame = new JFrame();
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(191, 230, 240));
        JMenuBar menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(5,5,5,5));
        menuBar.setFont(new Font("SansSerif",Font.BOLD,25));
        menuBar.setBounds(20,20,600,40);
        panelMenu.add(menuBar);
        frame.getContentPane().add(BorderLayout.NORTH,panelMenu);

        final JPanel panelBookList = new JPanel();
        panelBookList.setLayout(null);
        panelBookList.setBackground(new Color(180, 157, 216,215));

        final JTextField searchBook = new JTextField();
        searchBook.setBounds(200,80,250,25);
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(475,80,80,25);
        final JPanel panelSearchBook = new JPanel();
        panelSearchBook.setLayout(null);
        panelSearchBook.setBackground(new Color(180, 157, 216,215));
        panelSearchBook.setVisible(false);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(searchBook.getText() != null){
                   targetBookList = BibliotecaHandler.searchBook(searchBook.getText(),currentBookList);
                   bookArray = BibliotecaHandler.generateTableData(targetBookList);
                   panelBookList.setVisible(false);
                   panelSearchBook.setVisible(true);
                   JTable targetBookList = new JTable(bookArray,columnNames);
                   JScrollPane jsp = new JScrollPane(targetBookList);
                   jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                   jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                   jsp.setBounds(180,120,400,300);
                   panelSearchBook.add(jsp);
                   frame.getContentPane().add(panelSearchBook);
               }
            }
        });

        JTable bookList = new JTable(bookArray,columnNames);
        JScrollPane jsp = new JScrollPane(bookList);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(180,120,400,300);

        JButton checkoutBtn = new JButton("Check out");
        checkoutBtn.setBounds(600,365,120,25);
        JButton returnBtn = new JButton("Return");
        returnBtn.setBounds(600,400,120,25);

        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BibliotecaApp();
            }
        });

        panelBookList.add(searchBook);
        panelBookList.add(searchBtn);
        panelBookList.add(jsp);
        panelBookList.add(checkoutBtn);
        panelBookList.add(returnBtn);
        panelBookList.setVisible(false);
        frame.getContentPane().add(BorderLayout.CENTER,panelBookList);

        JMenuItem bookItem = new JMenuItem("Book List");
        menuBar.add(bookItem);
        bookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBookList.setVisible(true);
            }
        });

        JMenuItem invalidItem = new JMenuItem("Invalid option");
        menuBar.add(invalidItem);
        invalidItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBookList.setVisible(false);
                JOptionPane.showMessageDialog(null, "Invalid option!");
            }
        });

        frame.setSize(800,600);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
