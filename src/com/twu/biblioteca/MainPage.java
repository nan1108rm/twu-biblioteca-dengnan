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
public class MainPage{
    private JFrame frame;
    final static ArrayList<Book> originBookList = BibliotecaHandler.getOriginBookList();
    static ArrayList<Book> currentBookList = BibliotecaHandler.getCurrentBookList();
    final static ArrayList<Movie> originMovieList = BibliotecaHandler.getOriginMovieList();
    static ArrayList<Movie> currentMovieList = BibliotecaHandler.getCurrentMovieList();

    static Object[][] bookArray= BibliotecaHandler.generateTableDataForBook(currentBookList);
    static Object[][] movieArray= BibliotecaHandler.generateTableDataForMovie(currentMovieList);
    String[] columnNames = {"Book Name","Author","Published Year"};
    String[] columnNamesMovie = {"Movie Name","Director","Year","Rating"};
    static int selectedSearchedItem;

    ArrayList<User> loggedInUserList = UserHandler.searchUser(BibliotecaApp.loggedInUser,UserHandler.getUserList());
    String userInfoStr = "Library number: " + loggedInUserList.get(0).getLibraryNum() + "\nUser name: " +
            loggedInUserList.get(0).getUserName() + "\nEmail Address: " + loggedInUserList.get(0).getEmail() +
            "\nPhone Number: " + loggedInUserList.get(0).getPhone();

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

        final JPanel panelCenter = new JPanel();
        final CardLayout card = new CardLayout();
        panelCenter.setLayout(card);
        panelCenter.setVisible(true);
        frame.getContentPane().add(BorderLayout.CENTER,panelCenter);

        final JPanel mainPanelBook = new JPanel();
        mainPanelBook.setLayout(null);
        mainPanelBook.setBackground(new Color(180, 157, 216,215));
        panelCenter.add(mainPanelBook,"Book");

        final JPanel mainPanelMovie = new JPanel();
        mainPanelMovie.setLayout(null);
        mainPanelMovie.setBackground(new Color(180, 157, 216,215));
        panelCenter.add(mainPanelMovie,"Movie");

        final JPanel mainPanelUser = new JPanel();
        mainPanelUser.setLayout(null);
        mainPanelUser.setBackground(new Color(180, 157, 216,215));
        panelCenter.add(mainPanelUser,"User");

        final JTextPane userInforText = new JTextPane();
        userInforText.setText(userInfoStr);
        JScrollPane jsp3 = new JScrollPane(userInforText);
        jsp3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp3.setBounds(100,50,500,400);
        mainPanelUser.add(jsp3);

        final JTable movieListTable = new JTable(movieArray,columnNamesMovie);
        JScrollPane jsp2 = new JScrollPane(movieListTable);
        jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp2.setBounds(100,50,500,400);
        mainPanelMovie.add(jsp2);


        final JTable bookListTable = new JTable(bookArray,columnNames);
        JScrollPane jsp = new JScrollPane(bookListTable);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(100,50,500,400);
        mainPanelBook.add(jsp);

        final JMenuItem bookItem = new JMenuItem("Books");
        menuBar.add(bookItem);
        bookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(panelCenter,"Book");
            }
        });

        final JMenuItem movieItem = new JMenuItem("Movies");
        menuBar.add(movieItem);
        movieItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(panelCenter,"Movie");
            }
        });

        final JMenuItem userInfo = new JMenuItem("User Information");
        menuBar.add(userInfo);
        userInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(panelCenter,"User");
            }
        });

        final JPanel panelEast = new JPanel();
        panelEast.setLayout(new BoxLayout(panelEast,BoxLayout.Y_AXIS));
        panelEast.setVisible(true);
        JButton checkoutBtn = new JButton("Check out");
        JButton returnBtn = new JButton("Return");
        final JTextField returnBookField = new JTextField();
        returnBookField.setSize(50,25);
        panelEast.add(checkoutBtn);
        panelEast.add(returnBookField);
        panelEast.add(returnBtn);
        frame.getContentPane().add(BorderLayout.EAST,panelEast);

        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bookListTable.getSelectedRow()>=0){
                    selectedSearchedItem = bookListTable.getSelectedRow();
                    String searchedBookName = bookListTable.getValueAt(selectedSearchedItem,0).toString();
                    ArrayList<Book> searchedBookList = BibliotecaHandler.searchBook(searchedBookName,currentBookList);
                    BibliotecaHandler.checkOutBook(searchedBookList);
                }else if(movieListTable.getSelectedRow()>=0){
                    selectedSearchedItem = movieListTable.getSelectedRow();
                    String searchedMovieName = movieListTable.getValueAt(selectedSearchedItem,0).toString();
                    ArrayList<Movie> searchedMovieList = BibliotecaHandler.searchMovie(searchedMovieName,currentMovieList);
                    BibliotecaHandler.checkOutMovie(searchedMovieList);
                }else{
                    JOptionPane.showMessageDialog(null, "Please select a valid item.");
                }
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemStr = returnBookField.getText();
                if(itemStr != null && BibliotecaHandler.searchMovie(itemStr,originMovieList).size()>0){
                    ArrayList<Movie> searchedMovieList = BibliotecaHandler.searchMovie(itemStr,originMovieList);
                    BibliotecaHandler.returnMovie(searchedMovieList);
                }else if(itemStr != null && BibliotecaHandler.searchBook(itemStr,originBookList).size()>0) {
                    ArrayList<Book> searchedBookList = BibliotecaHandler.searchBook(itemStr, originBookList);
                    BibliotecaHandler.returnBook(searchedBookList);
                }else{
                    JOptionPane.showMessageDialog(null, "Please select a book.");
                }
            }
        });

        frame.setSize(800,600);
        frame.setVisible(true);
    }
}
