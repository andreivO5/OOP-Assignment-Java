package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Screen extends JFrame implements ActionListener, FocusListener
{
    // All the GUI elements are initialised here
    JPanel panel;
    JButton searchButton, newSearch, pathButton;
    JTextField searchField, pathField;
    JTextArea searchArea;
    JLabel titleLabel, pathLabel, titleIcon, searchLabel;

    // extra variables for search engine features
    public String search = null;
    public String changePath = null;
    public String pathname = "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\textfiles";

    // optional search icons
    Icon searchIcon = new ImageIcon(
            "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\src\\resources\\search2.png");
    Icon titleImage = new ImageIcon(
            "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\src\\resources\\titlesearch.png");

    public Screen(String title)
    {
        // giving the JFrame its properties
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);

        // giving the JPanel its properties
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        // titleLabel and its properties
        titleLabel = new JLabel("AVSearch");
        panel.add(titleLabel);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(350,20,300,60);
        titleLabel.setForeground(Color.white);

        // titleIcon
        titleIcon = new JLabel(titleImage);
        panel.add(titleIcon);
        titleIcon.setBounds(660,15,60,60);

        // the main searchField with a FocusListener
        searchField = new JTextField();
        panel.add(searchField);
        searchField.addFocusListener(this);
        searchField.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        Font textFieldFont = new Font("Arial", Font.BOLD, 20);
        searchField.setFont(textFieldFont);
        searchField.setBounds(140, 100, 600, 40);

        // searchLabel for instruction on using logic
        searchLabel = new JLabel("Tip: Use '&&' to separate two words for a logic search.");
        panel.add(searchLabel);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        searchLabel.setBounds(190,150,550,40);
        searchLabel.setForeground(Color.white);

        // main searchButton with an ActionListener
        searchButton = new JButton("");
        searchButton.setIcon(searchIcon);
        panel.add(searchButton);
        searchButton.setBackground(Color.white);
        searchButton.addActionListener(this);
        searchButton.setToolTipText("Search for the term above.");
        searchButton.setBounds(770, 100, 80, 40);

        // The text box where search results appear
        searchArea = new JTextArea();
        searchArea.setColumns(70);
        searchArea.setRows(6);
        searchArea.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        searchArea.setFont(new Font("Arial", Font.BOLD, 18));
        searchArea.setBounds(40, 100, 600, 700);
        searchArea.setForeground(Color.white);
        searchArea.setBackground(Color.black);

        // button to trigger a new search, with an ActionListener
        newSearch = new JButton("New Search");
        newSearch.setBackground(Color.green);
        newSearch.addActionListener(this);
        newSearch.setToolTipText("Search for another word.");
        newSearch.setBounds(680, 300, 140, 40);

        // Label, text field and button to change the search space.
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

        frame.setVisible(true); // this line prevents issues with the frame components not always appearing

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Action when the search button is pressed
        if (e.getSource()== searchButton)
        {
            // Error checking, if the search field is empty
            if (searchField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please input a search term.");
            }
            else
            {
                // components are temporarily removed to allow space for the search results box
                panel.remove(searchField);
                panel.remove(searchButton);
                panel.remove(searchLabel);
                // search results text box is added to the panel, with the newSearch button
                panel.add(searchArea);
                panel.add(newSearch);
                // the 2 lines below assure that the correct components are removed/added
                panel.revalidate();
                panel.repaint();

                // text is written to the Search results text box
                searchArea.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                searchArea.append("Searching for the word: "+ search + "\n");
                searchArea.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                searchArea.append("Search Space:\n"+ pathname + "\n");
                searchArea.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

                // a directory filetype is created using the pathname
                File directoryPath = new File(pathname);
                // an array of the list of files in the directory is created
                File filesList[] = directoryPath.listFiles();

                // the loop below continues for as long as there is a new file in the filesList
                for(File file : filesList)
                {
                    // FileProcessor object is constructed with the current filepath and filename
                    FileProcessor currentfile = new FileProcessor(file.getAbsolutePath(), file.getName());
                    // the returned string from SearchFile is appended to the Search Results text box.
                    searchArea.append("\n" + currentfile.SearchFile(search));
                }
            }
        }
        if (e.getSource() == newSearch) // action taken when the newSearch button is pressed
        {
            // To initiate a new search, text box and newSearch button is removed
            panel.remove(searchArea);
            panel.remove(newSearch);
            // Previous search components are re-added to the panel.
            panel.add(searchField);
            panel.add(searchButton);
            panel.add(searchLabel);
            // the 2 lines below assure that the correct components are removed/added
            panel.revalidate();
            panel.repaint();
            // the line below clears the search results text box.
            searchArea.setText("");
        }
        if (e.getSource() == pathButton) // action taken when the pathButton is pressed
        {
            // error checking, if the path field is empty.
            if (pathField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid pathname");
            }
            else
            {
                // new pathname is assigned from input
                pathname = changePath;
                JOptionPane.showMessageDialog(this, "Search location set to: "+ pathname);
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e)
    {
        // focusLost used for the textFields so that the user
        // does not have to always press 'Enter' to initialise variable
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
