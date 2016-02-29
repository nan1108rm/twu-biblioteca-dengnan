package com.twu.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 * Created by dengnan on 16/2/29.
 */
public class MainPage implements ActionListener{
    private JFrame frame;
    final static ArrayList<Book> originBookList = BibliotecaHandler.initBookList();
    static ArrayList<Book> currentBookList = originBookList;
    static Object[][] bookArray= BibliotecaHandler.generateTableData(currentBookList);
    String[] columnNames = {"Book Name","Author","Published Year"};
    static ArrayList<Book> targetBookList = new ArrayList<Book>();
    static int selectedSearchedBook;
    static JTable targetBookTable;

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

        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setVisible(true);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);

        final JPanel mainPanelNorth = new JPanel();
        mainPanelNorth.setBackground(new Color(180, 157, 216,215));
        final JTextField searchBook = new JTextField();
        searchBook.setColumns(20);
        JButton searchBtn = new JButton("Search");
        mainPanelNorth.add(searchBook);
        mainPanelNorth.add(searchBtn);
        mainPanelNorth.setVisible(true);
        mainPanel.add(BorderLayout.NORTH,mainPanelNorth);

        final JPanel mainPanelCenter = new JPanel();
        mainPanelCenter.setLayout(null);
        mainPanelCenter.setBackground(new Color(180, 157, 216,215));
        final JPanel panelSearchBook = new JPanel();
        panelSearchBook.setLayout(null);
        panelSearchBook.setBackground(new Color(180, 157, 216,215));
        panelSearchBook.setVisible(false);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(searchBook.getText() != null){
                   targetBookList = BibliotecaHandler.searchBook(searchBook.getText(),originBookList);
                   bookArray = BibliotecaHandler.generateTableData(targetBookList);
                   mainPanelCenter.setVisible(false);
                   panelSearchBook.setVisible(true);
                   targetBookTable = new JTable(bookArray,columnNames);
                   JScrollPane jsp = new JScrollPane(targetBookTable);
                   jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                   jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                   jsp.setBounds(100,50,500,400);
                   panelSearchBook.add(jsp);
                   mainPanel.add(BorderLayout.CENTER,panelSearchBook);
               }
            }
        });

        JTable bookListTable = new JTable(bookArray,columnNames);
        JScrollPane jsp = new JScrollPane(bookListTable);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(100,50,500,400);
        mainPanelCenter.add(jsp);
        mainPanelCenter.setVisible(false);
        mainPanel.add(BorderLayout.CENTER,mainPanelCenter);

        final JPanel mainPanelEast = new JPanel();
        mainPanelEast.setLayout(new BoxLayout(mainPanelEast,BoxLayout.Y_AXIS));
        mainPanelEast.setVisible(true);
        JButton checkoutBtn = new JButton("Check out");
        JButton returnBtn = new JButton("Return");
        mainPanelEast.add(checkoutBtn);
        mainPanelEast.add(returnBtn);
        mainPanel.add(BorderLayout.EAST,mainPanelEast);

        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSearchedBook = targetBookTable.getSelectedRow();
                if(selectedSearchedBook >= 0){
                    String searchedBookName = targetBookTable.getValueAt(selectedSearchedBook,0).toString();
                    ArrayList<Book> searchedBookList = BibliotecaHandler.searchBook(searchedBookName,currentBookList);
                    if( searchedBookList.size() >= 0){
                        currentBookList.remove(searchedBookList.get(0));
                        JOptionPane.showMessageDialog(null,"Thank you! Enjoy the book!");
                    }else{
                        JOptionPane.showMessageDialog(null,"That book is not avaliable!");
                    }
                }
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSearchedBook = targetBookTable.getSelectedRow();
                if(selectedSearchedBook >= 0){
                    String searchedBookName = targetBookTable.getValueAt(selectedSearchedBook,0).toString();
                    ArrayList<Book> searchedBookList = BibliotecaHandler.searchBook(searchedBookName,originBookList);
                    if( searchedBookList.size() >= 0){
                        currentBookList.add(searchedBookList.get(0));
                        JOptionPane.showMessageDialog(null,"Thank you for returning the book!");
                    }else{
                        JOptionPane.showMessageDialog(null,"That is not a valid book to return!");
                    }
                }
            }
        });

        JMenuItem bookItem = new JMenuItem("Book List");
        menuBar.add(bookItem);
        bookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanelCenter.setVisible(true);
            }
        });

        JMenuItem invalidItem = new JMenuItem("Invalid option");
        menuBar.add(invalidItem);
        invalidItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanelCenter.setVisible(false);
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
