package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Screen extends JFrame implements ActionListener, FocusListener
{

    JPanel panel;
    JButton searchButton, newSearch, pathButton;
    JTextField searchField, pathField;
    JTextArea searchArea;
    JLabel titleLabel, pathLabel, titleIcon, searchLabel;

    public String search = null;
    public String changePath = null;
    public String pathname = "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\textfiles";

    Icon searchIcon = new ImageIcon(
            "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\src\\resources\\search2.png");
    Icon titleImage = new ImageIcon(
            "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\src\\resources\\titlesearch.png");

    public Screen(String title)
    {
        JFrame frame1 = new JFrame(title);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1000,1000);
        frame1.setLocationRelativeTo(null);

        panel = new JPanel();
        frame1.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        titleLabel = new JLabel("AVSearch");
        panel.add(titleLabel);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(350,20,300,60);
        titleLabel.setForeground(Color.white);

        titleIcon = new JLabel(titleImage);
        panel.add(titleIcon);
        titleIcon.setBounds(660,15,60,60);

        searchField = new JTextField();
        panel.add(searchField);
        searchField.addFocusListener(this);
        searchField.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        Font textFieldFont = new Font("Arial", Font.BOLD, 20);
        searchField.setFont(textFieldFont);
        searchField.setBounds(140, 100, 600, 40);

        searchLabel = new JLabel("Tip: Use '&&' to separate two words for a logic search.");
        panel.add(searchLabel);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        searchLabel.setBounds(190,150,550,40);
        searchLabel.setForeground(Color.white);

        searchButton = new JButton("");
        searchButton.setIcon(searchIcon);
        panel.add(searchButton);
        searchButton.setBackground(Color.white);
        searchButton.addActionListener(this);
        searchButton.setToolTipText("Search for the term above.");
        searchButton.setBounds(770, 100, 80, 40);

        searchArea = new JTextArea();
        searchArea.setColumns(70);
        searchArea.setRows(6);
        searchArea.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        searchArea.setFont(new Font("Arial", Font.BOLD, 18));
        searchArea.setBounds(40, 100, 600, 700);
        searchArea.setForeground(Color.white);
        searchArea.setBackground(Color.black);

        newSearch = new JButton("New Search");
        newSearch.setBackground(Color.green);
        newSearch.addActionListener(this);
        newSearch.setToolTipText("Search for another word.");
        newSearch.setBounds(680, 300, 140, 40);

        pathLabel = new JLabel("Change the search space");
        panel.add(pathLabel);
        pathLabel.setFont(new Font("Arial", Font.BOLD, 30));
        pathLabel.setBounds(300,840,400,40);
        pathLabel.setForeground(Color.white);

        pathField = new JTextField();
        panel.add(pathField);
        pathField.addFocusListener(this);
        pathField.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        pathField.setFont(textFieldFont);
        pathField.setBounds(80, 890, 540, 30);

        pathButton = new JButton("Apply Search Space");
        panel.add(pathButton);
        pathButton.setBackground(Color.green);
        pathButton.addActionListener(this);
        pathButton.setToolTipText("This button applies the new search space.");
        pathButton.setBounds(660, 890, 200, 30);

        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()== searchButton)
        {
            if (searchField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please input a search term.");
            }
            else
            {
                panel.remove(searchField);
                panel.remove(searchButton);
                panel.remove(searchLabel);
                panel.add(searchArea);
                panel.add(newSearch);
                panel.revalidate();
                panel.repaint();

                searchArea.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                searchArea.append("Searching for the word: "+ search + "\n");
                searchArea.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                searchArea.append("Search Space:\n"+ pathname + "\n");
                searchArea.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                File directoryPath = new File(pathname);
                File filesList[] = directoryPath.listFiles();

                for(File file : filesList)
                {
                    FileProcessor currentfile = new FileProcessor(file.getAbsolutePath(), file.getName());
                    searchArea.append("\n" + currentfile.SearchFile(search));
                }
            }
        }
        if (e.getSource() == newSearch)
        {
            panel.remove(searchArea);
            panel.remove(newSearch);
            panel.add(searchField);
            panel.add(searchButton);
            panel.add(searchLabel);
            panel.revalidate();
            panel.repaint();

            searchArea.setText("");
        }
        if (e.getSource() == pathButton)
        {
            if (pathField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid pathname");
            }
            else
            {
                pathname = changePath;
                JOptionPane.showMessageDialog(this, "Search location set to: "+ pathname);
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if (e.getSource() == searchField)
        {
            search = searchField.getText();
        }
        if (e.getSource() == pathField)
        {
            changePath = pathField.getText();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }
}
