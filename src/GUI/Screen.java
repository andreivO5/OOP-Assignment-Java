package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Screen extends JFrame implements ActionListener, FocusListener
{

    JPanel panel1;
    JButton button1, newsearch, pathButton;
    JTextField textField1, pathField;
    JTextArea textArea1;
    JLabel label1, pathLabel;

    public String search = null;
    public String changePath = null;
    public String pathname = "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\textfiles";

    public Screen(String title)
    {
        JFrame frame1 = new JFrame(title);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1000,800);
        frame1.setLocationRelativeTo(null);

        panel1 = new JPanel();
        frame1.add(panel1);
        panel1.setLayout(null);
        panel1.setBackground(Color.gray);

        label1 = new JLabel("AVSearch");
        panel1.add(label1);
        label1.setFont(new Font("Arial", Font.BOLD, 60));
        label1.setBounds(350,20,600,60);

        textField1 = new JTextField();
        panel1.add(textField1);
        textField1.addFocusListener(this);
        textField1.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        Font textFieldFont = new Font("Arial", Font.BOLD, 20);
        textField1.setFont(textFieldFont);
        textField1.setBounds(140, 100, 600, 40);

        button1 = new JButton("Search");
        panel1.add(button1);
        button1.setBackground(Color.green);
        button1.addActionListener(this);
        button1.setToolTipText("Search for the term above.");
        button1.setBounds(770, 100, 80, 40);

        textArea1 = new JTextArea("Search Results:");
        textArea1.setColumns(70);
        textArea1.setRows(6);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        textArea1.setBounds(200, 100, 400, 500);

        newsearch = new JButton("New Search");
        newsearch.setBackground(Color.green);
        newsearch.addActionListener(this);
        newsearch.setToolTipText("Search for another word.");
        newsearch.setBounds(620, 250, 140, 40);

        pathLabel = new JLabel("Change the search space");
        panel1.add(pathLabel);
        pathLabel.setFont(new Font("Arial", Font.BOLD, 30));
        pathLabel.setBounds(300,600,400,40);

        pathField = new JTextField();
        panel1.add(pathField);
        pathField.addFocusListener(this);
        pathField.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        pathField.setFont(textFieldFont);
        pathField.setBounds(80, 650, 540, 30);

        pathButton = new JButton("Apply Search Space");
        panel1.add(pathButton);
        pathButton.setBackground(Color.green);
        pathButton.addActionListener(this);
        pathButton.setToolTipText("This button applies the new search space.");
        pathButton.setBounds(660, 650, 200, 30);



        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==button1)
        {
            if (textField1.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please input a search term.");
            }
            else
            {
                panel1.remove(textField1);
                panel1.remove(button1);
                panel1.add(textArea1);
                panel1.add(newsearch);
                panel1.revalidate();
                panel1.repaint();

                textArea1.append("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                textArea1.append("Searching for the word: "+ search + "\n");
                textArea1.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                File directoryPath = new File(pathname);
                File filesList[] = directoryPath.listFiles();

                for(File file : filesList)
                {
                    FileProcessor currentfile = new FileProcessor(file.getAbsolutePath(), file.getName());
                    textArea1.append("\n" + currentfile.SearchFile(search));
                }
            }
        }
        if (e.getSource() == newsearch)
        {
            panel1.remove(textArea1);
            panel1.remove(newsearch);
            panel1.add(textField1);
            panel1.add(button1);
            panel1.revalidate();
            panel1.repaint();

            textArea1.setText("Search Results:\n");
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
        if (e.getSource() == textField1)
        {
            search = textField1.getText();
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
