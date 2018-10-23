package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
import java.sql.*;

public class GUI {
    public JPanel main_panel;
    private JPanel login_panel;
    private JScrollPane operation_panel;
    private JTextField login_JDBC_driver;
    private JTextField login_URL;
    private JTextField login_user;
    private JButton login_Button;
    private JPasswordField login_password;
    private JButton help_button;
    private JButton logout_button;
    private JTextArea text_output;
    private JComboBox comboBox1;
    private JPanel simple_mode;
    private JTextArea text_command;
    private JButton command_run;
    private JButton simple_run;
    private JTextArea text_sample;

    private Statement statement;
    ResultSet result;
    int columnsNumber;
    ResultSetMetaData rsmd;
    // static String;
    // static String;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    SQL connc = new SQL();

    public GUI() {
        text_output.setBackground(new Color(69, 73, 74, 0));
        text_sample.setBackground(new Color(69, 73, 74, 0));

        login_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // login action
                // String driver = login_JDBC_driver.getText();
                String URL = login_URL.getText();
                String user = login_user.getText();
                String password = login_password.getText();
                // String error_message = "Login information is not correct, please try again";

                // for test login
                user = "zzk";
                password = "123";
                URL = "jdbc:mysql://localhost:3306/test";


                boolean sign = connc.connect(URL, user, password);
                if (sign == true)
                {
                    // switch to operation page
                    JOptionPane.showMessageDialog(null, "login success.");
                    main_panel.removeAll();
                    main_panel.add(operation_panel);
                    main_panel.repaint();
                    main_panel.revalidate();
                }
                else
                    JOptionPane.showMessageDialog(null, "Login information is not correct, please try again");
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
                JOptionPane.showMessageDialog(null, "1. you can check if there are tables exist or not by 'SHOW TABLES'\n" +
                        "2. if table exist, you can check each table by 'DESC table_name'. if not you can create the table with sample CREATE TABLE.\n" +
                        "3. you can start with sample SELECT to check if there are any data in the table.\n" +
                        "4. you can also check sample from ADD ROWS to add new date into a table, or sample from UPDATE ROWS to modify data in the table, or sample from DELETE ROWS to delete data from the table.\n" +
                        "5. you can check sample from DELETE TABLE to delete a table.\n" +
                        "\n" +
                        "if you want you can check all the other sample that we provide to have more understanding about SQL, or you can also search online and try it out in this software.");
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String value = comboBox1.getSelectedItem().toString();
                // JTextField textField1 = new JTextField();
                // JTextPane textPane1 = new JTextPane();
                // // textField1.setMinimumSize(new Dimension(100,30));
                // textField1.setPreferredSize(new Dimension(100,24));
                // textPane1.setPreferredSize(new Dimension(100,24));

                // initialize sample
                sample sample =  new sample();

                // if(value == "SELECT") {
                //     text_sample.setText(sample.select);
                //     // simple_mode.add(textField1);
                //     // simple_mode.repaint();
                //     // simple_mode.revalidate();
                //     // comboBox1.disable();
                // }
                // else if(value == "SELECT_")


                // get selection index
                int index = comboBox1.getSelectedIndex();
                switch (index) {
                    case 0:
                        text_sample.setText(sample.select);
                        break;

                    case 1:
                        text_sample.setText(sample.select_case);
                        break;

                    case 2:
                        text_sample.setText(sample.join);
                        break;

                    case 3:
                        text_sample.setText(sample.select_others);
                        break;

                    case 4:
                        text_sample.setText(sample.with_select);
                        break;

                    case 5:
                        text_sample.setText(sample.as);
                        break;

                    case 6:
                        text_sample.setText(sample.create);
                        break;

                    case 7:
                        text_sample.setText(sample.drop);
                        break;

                    case 8:
                        text_sample.setText(sample.insert);
                        break;

                    case 9:
                        text_sample.setText(sample.update);
                        break;

                    case 10:
                        text_sample.setText(sample.delete);
                        break;

                    default:
                        break;
                }
            }
        });
        command_run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // command line input
                String SQL_code = text_command.getText();
                result = null;
                String return_result = connc.command(SQL_code);
                System.out.println(return_result);



                // try {
                //     result = statement.executeQuery(SQL_code);
                // } catch (SQLException e1) {
                //     e1.printStackTrace();
                // }
                // String output = "";



                // try {

                //     while (result.next()) {
                //         String temp = null;
                //         try {
                //             temp = result.getString("EM_ID");
                //         } catch (SQLException e1) {
                //             JOptionPane.showMessageDialog(null, "SQL code fail 1.");
                //             e1.printStackTrace();
                //         }
                //         output = temp.replace("\n", ",");
                //         System.out.println(output);
                //     }
                // }
                // catch (SQLException e2) {
                //     JOptionPane.showMessageDialog(null, "SQL code fail 2.");
                //     e2.printStackTrace();
                // }


                // try {
                //     rsmd = result.getMetaData();
                //     columnsNumber = rsmd.getColumnCount();
                // } catch (SQLException e1) {
                //     e1.printStackTrace();
                // }
                // try {
                //     while (result.next()) {
                //         for (int i = 1; i <= columnsNumber; i++) {
                //             if (i > 1) System.out.print(",  ");
                //             String columnValue = result.getString(i);
                //             try {
                //                 // System.out.print(columnValue + " " + rsmd.getColumnName(i));
                //                 output += columnValue + " " + rsmd.getColumnName(i);
                //             } catch (SQLException e1) {
                //                 e1.printStackTrace();
                //             }
                //         }
                //         // System.out.println("");
                //         output += "\n";
                //     }
                // }
                // catch (SQLException e1) {
                //     e1.printStackTrace();
                // }

                text_output.setText(return_result);
            }
        });
    }
}
