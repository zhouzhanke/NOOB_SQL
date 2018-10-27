package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class GUI {
    public JPanel main_panel;
    private JPanel login_panel;
    private JScrollPane operation_panel;
    private JTextField login_URL;
    private JTextField login_user;
    private JButton login_Button;
    private JPasswordField login_password;
    private JButton help_button;
    private JButton logout_button;
    private JTextArea text_output;
    // private JComboBox combo_simple;
    private JTextArea text_command;
    private JButton command_run;
    private JButton simple_run;
    private JTextArea text_sample;
    private JComboBox combo_font_size;
    private JButton clean_output;
    private JPanel command_mode_panel;
    private JPanel sample_panel;
    private JPanel output_panel;
    private JPanel simple_mode_panel;
    private JLabel font_size_panel;
    private JPanel simple_stage;
    private JComboBox simple_stage_1;


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    SQL connc = new SQL();

    public GUI() {
        // back ground color for output panel and sample panel
        Color background_color = new Color(69, 73, 74, 0);
        text_output.setBackground(background_color);
        text_sample.setBackground(background_color);

        // system font
        final Font[] system_font = {null};
        try {
            // create font
            InputStream myStream = new BufferedInputStream(new FileInputStream("OxygenMono-Regular.ttf"));
            system_font[0] = Font.createFont(Font.TRUETYPE_FONT, myStream);

            // // register font
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(system_font[0]);
            // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("OxygenMono-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // Font font = new Font("OxygenMono-Regular", Font.TRUETYPE_FONT, 12);
        system_font[0] = system_font[0].deriveFont(Font.TRUETYPE_FONT, 14);
        // simple_mode_panel.setFont(system_font[0]);
        // command_mode_panel.setFont(system_font[0]);
        // sample_panel.setFont(system_font[0]);
        // output_panel.setFont(system_font[0]);

        help_button.setFont(system_font[0]);
        logout_button.setFont(system_font[0]);
        simple_run.setFont(system_font[0]);
        command_run.setFont(system_font[0]);
        clean_output.setFont(system_font[0]);
        combo_font_size.setFont(system_font[0]);
        font_size_panel.setFont(system_font[0]);

        text_output.setFont(system_font[0]);
        text_sample.setFont(system_font[0]);
        text_command.setFont(system_font[0]);

        // load login data from last time
        try {
            BufferedReader reader = new BufferedReader(new FileReader("login.txt"));
            login_URL.setText(reader.readLine());
            login_user.setText(reader.readLine());
            login_password.setText(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        login_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // login  and switch to operation panel
                login();
            }
        });
        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dis-connection from database
                connc.exit_DB();

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
        simple_stage_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String value = combo_simple.getSelectedItem().toString();
                // JTextField textField1 = new JTextField();
                // JTextPane textPane1 = new JTextPane();
                // // textField1.setMinimumSize(new Dimension(100,30));
                // textField1.setPreferredSize(new Dimension(100,24));
                // textPane1.setPreferredSize(new Dimension(100,24));

                // initialize sample
                sample sample = new sample();

                // if(value == "SELECT") {
                //     text_sample.setText(sample.select);
                //     // simple_mode.add(textField1);
                //     // simple_mode.repaint();
                //     // simple_mode.revalidate();
                //     // combo_simple.disable();
                // }
                // else if(value == "SELECT_")


                // get selection index
                int index = simple_stage_1.getSelectedIndex();
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
                output();
            }
        });
        combo_font_size.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change font
                int font_index = combo_font_size.getSelectedIndex();
                int font_size = font_index + 10;

                system_font[0] = system_font[0].deriveFont(Font.TRUETYPE_FONT, font_size);

                // simple_mode_panel.setFont(system_font[0]);
                // command_mode_panel.setFont(system_font[0]);
                // sample_panel.setFont(system_font[0]);
                // output_panel.setFont(system_font[0]);

                help_button.setFont(system_font[0]);
                logout_button.setFont(system_font[0]);
                simple_run.setFont(system_font[0]);
                command_run.setFont(system_font[0]);
                clean_output.setFont(system_font[0]);
                combo_font_size.setFont(system_font[0]);
                font_size_panel.setFont(system_font[0]);

                text_output.setFont(system_font[0]);
                text_sample.setFont(system_font[0]);
                text_command.setFont(system_font[0]);

                main_panel.revalidate();
            }
        });
        clean_output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_output.setText("");
            }
        });
        text_command.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(e.getKeyCode() == 10 && e.isControlDown())
                {
                    // command line input
                    output();
                }
            }
        });
        login_URL.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                // System.out.print("key: " + e.getKeyCode() + "\n");
                if(e.getKeyCode() == 0)
                {
                    // login  and switch to operation panel
                    login();
                }
            }
        });
        login_user.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                // System.out.print("key: " + e.getKeyCode() + "\n");
                if(e.getKeyCode() == 0)
                {
                    // login  and switch to operation panel
                    login();
                }
            }
        });
        login_password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                // System.out.print("key: " + e.getKeyCode() + "\n");
                if(e.getKeyCode() == 0)
                {
                    // login  and switch to operation panel
                    login();
                }
            }
        });
    }

    private void login() {
        // login action
        String URL = login_URL.getText();
        String user = login_user.getText();
        String password = login_password.getText();

        // for test login
        // URL = "jdbc:mysql://localhost:3306/test";
        // user = "zzk";
        // password = "123";


        boolean sign = connc.connect(URL, user, password);
        if (sign == true) {
            // switch to operation page
            JOptionPane.showMessageDialog(null, "login success.");

            // save login information
            File file = new File("login.txt");

            //Create the file
            try {
                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }

                //Write Content
                FileWriter writer = null;
                writer = new FileWriter(file);
                writer.write(URL + "\n" + user + "\n" + password);
                writer.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            main_panel.removeAll();
            main_panel.add(operation_panel);
            main_panel.repaint();
            main_panel.revalidate();
        } else
            JOptionPane.showMessageDialog(null, "Login information is not correct, please try again");
    }

    private void output() {
        // command line input
        String SQL_code = text_command.getText();
        String return_result = connc.command(SQL_code);
        System.out.println(return_result);
        text_output.append(return_result + "\n");
    }
}
