package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GUI {
    private JPanel main_panel;
    private JPanel login_panel;
    private JScrollPane operation_panel;
    private JTextField login_JDBC_driver;
    private JTextField login_URL;
    private JTextField login_user;
    private JButton login_Button;
    private JPasswordField login_password;
    private JButton help_button;
    private JButton logout_button;
    private JLabel text_sample;
    private JLabel text_output;
    private JComboBox comboBox1;
    private JButton button1;
    private JPanel simple_mode;
    private JTextArea text_command;
    private JTextField textField1;

    public GUI() {
        login_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // login action
                String driver = login_JDBC_driver.getText();
                String URL = login_URL.getText();
                String user = login_user.getText();
                String password = login_password.getText();
                String error_message = "Login information is not correct, please try again";


                try{
                    Class.forName(driver);
                    Connection connection = DriverManager.getConnection(URL, user, password);

                    // switch to operation page
                }
                catch (SQLException e1)
                {
                    JOptionPane.showMessageDialog(null, error_message);
                }
                catch (Exception e2)
                {
                    JOptionPane.showMessageDialog(null, error_message);
                }
                main_panel.removeAll();
                main_panel.add(operation_panel);
                main_panel.repaint();
                main_panel.revalidate();
            }
        });
        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to login page
                main_panel.removeAll();
                main_panel.add(login_panel);
                main_panel.repaint();
                main_panel.revalidate();
            }
        });
        help_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = comboBox1.getSelectedItem().toString();
                JTextField textField1 = new JTextField();
                JTextPane textPane1 = new JTextPane();
                // textField1.setMinimumSize(new Dimension(100,30));
                textField1.setPreferredSize(new Dimension(100,24));
                textPane1.setPreferredSize(new Dimension(100,24));
                if(value == "SELECT") {
                    simple_mode.add(textField1);
                    simple_mode.repaint();
                    simple_mode.revalidate();
                    // comboBox1.disable();
                }

                if(value == "NO") {
                    simple_mode.removeAll();
                    simple_mode.add(comboBox1);
                    simple_mode.repaint();
                    simple_mode.revalidate();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame main_frame = new JFrame("NOOB SQL");
        main_frame.setContentPane(new GUI().main_panel);
        main_frame.setPreferredSize(new Dimension(1280,720));
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.pack();
        main_frame.setVisible(true);
    }
}
