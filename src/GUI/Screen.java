package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Screen extends JFrame implements ActionListener, MouseListener, FocusListener
{

    JPanel panel1;
    JButton button1, newsearch;
    JTextField textField1;
    JTextArea textArea1;
    JLabel label1;

    String search;

    String pathname = "C:\\Users\\blaze\\Desktop\\oop_java\\Java Assignment OOP\\textfiles";

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

        textArea1 = new JTextArea("Search Results:\n");
        textArea1.setColumns(70);
        textArea1.setRows(6);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        textArea1.setBounds(200, 100, 400, 500);

        newsearch = new JButton("New Search");
        newsearch.setBackground(Color.green);
        newsearch.addActionListener(this);
        newsearch.setToolTipText("Search for another word.");
        newsearch.setBounds(620, 250, 140, 40);




        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==button1)
        {
            panel1.remove(textField1);
            panel1.remove(button1);
            panel1.add(textArea1);
            panel1.add(newsearch);
            panel1.revalidate();
            panel1.repaint();

            textArea1.append("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
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

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if (e.getSource() == textField1)
        {
            search = textField1.getText();
        }
    }
}
