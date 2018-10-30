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
    public JPanel simple_command;
    private JPanel select_panel;
    private JComboBox select_table_name;
    private JComboBox select_column_name_1;
    private JTextField select_condition;
    private JButton select_plus;
    private JComboBox select_column_name_2;
    private JComboBox select_column_name_3;
    private JComboBox select_column_name_4;
    private JComboBox select_column_name_5;
    private JButton select_minus;
    private JPanel create_panel;
    private JTextField textField1;
    private JButton createColumnButton;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel create_column_main_panel;
    private JPanel column_panel;
    private JButton create_table_plus;
    private JButton create_table_minus;
    private JSpinner create_table_column_number;
    private JPanel panel_404;
    private int select_column_total = 5;
    private int select_column_current = 1;
    private JComboBox select_column_name[] = {this.select_column_name_1, this.select_column_name_2,
            this.select_column_name_3, this.select_column_name_4, this.select_column_name_5};
    private JPanel create_table_panels[] = {column_panel};
    private int create_table_count = 1;

    private String simple_reult = null;


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    SQL connc = new SQL();

    //varibles for select
    String table_name[] = null;
    String column_name[] = null;

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
        select_table_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select_column_current = 1;

                for (int i = 1; i < select_column_total; i++)
                    select_column_name[i].setVisible(false);

                String table = (String)select_table_name.getSelectedItem();
                for (int i = 0; i < select_column_total; i++)
                    select_column_name[i].removeAllItems();
                select_column_name_1.removeAllItems();
                int count = connc.get_column_name(table);
                String column_name[] = connc.column_name;
                for (int i = 0; i < count; i++)
                {
                    for (int j = 0; j < select_column_total; j++)
                        select_column_name[j].addItem(column_name[i]);
                }
                select_column_name_1.addItem("*");
                select_column_name_1.setSelectedIndex(connc.column_count);
            }
        });

        select_plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (select_column_current == select_column_total) {
                    JOptionPane.showMessageDialog(null,
                            "The maximum of column is 5, can not be over it.");
                    return;
                }
                select_column_name_1.setSelectedIndex(0);
                select_column_current++;
                if (select_column_current == 2)
                    select_column_name[0].removeItemAt(connc.column_count);
                select_column_name[select_column_current - 1].setVisible(true);
            }
        });
        select_minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(select_column_current == 1) {
                    JOptionPane.showMessageDialog(null, "This is the last one.");
                    return;
                }

                select_column_current--;
                if (select_column_current == 1) {
                    select_column_name[0].addItem("*");
                    select_column_name[0].setSelectedIndex(connc.column_count);
                }
                select_column_name[select_column_current].setVisible(false);
            }
        });

        simple_run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = simple_stage_1.getSelectedIndex();

                switch (index)
                {
                    case 1:
                        select_result();
                        text_output.append(simple_reult);
                        break;
                }

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
                int index = simple_stage_1.getSelectedIndex() - 1;
                switch (index) {
                    case 0:
                        text_sample.setText(sample.select);
                        simple_command.removeAll();
                        simple_command.add(select_panel);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 1:
                        text_sample.setText(sample.select_case);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 2:
                        text_sample.setText(sample.join);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 3:
                        text_sample.setText(sample.select_others);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 4:
                        text_sample.setText(sample.with_select);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 5:
                        text_sample.setText(sample.as);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 6:
                        text_sample.setText(sample.create);
                        /*simple_command.removeAll();
                        simple_command.add(create_panel);
                        create_column_main_panel.removeAll();
                        simple_command.repaint();
                        simple_command.revalidate();*/
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 7:
                        text_sample.setText(sample.drop);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();

                        break;

                    case 8:
                        text_sample.setText(sample.insert);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 9:
                        text_sample.setText(sample.update);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    case 10:
                        text_sample.setText(sample.delete);
                        simple_command.removeAll();
                        simple_command.add(panel_404);
                        simple_command.repaint();
                        simple_command.revalidate();
                        break;

                    default:
                        simple_command.removeAll();
                        simple_command.repaint();
                        simple_command.revalidate();
                        text_sample.setText("");
                        break;
                }
            }
        });
        createColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_column_main_panel.removeAll();

                int num = (int)create_table_column_number.getValue();
                System.out.println(num);
                create_table_panels = new JPanel[num];
                for (int i = 0; i < num; i++)
                {
                    create_table_panels[i] = column_panel;
                }

                create_column_main_panel.repaint();
                create_column_main_panel.revalidate();
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
                // System.out.print("key: " + e.getKeyCode() + "\n");
                if(e.getKeyCode() == 10 && e.isControlDown())
                {
                    // command line input
                    output();
                }
            }
        });
        login_URL.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                // System.out.print("key: " + e.getKeyCode() + "\n");
                if(e.getKeyCode() == 10)
                {
                    // login  and switch to operation panel
                    login();
                }
            }
        });
        login_user.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == 10)
                {
                    // login  and switch to operation panel
                    login();
                }
            }
        });
        login_password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == 10)
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
            simple_command.removeAll();
            SpinnerNumberModel limit_model = new SpinnerNumberModel(1, 1, 100, 1);
            create_table_column_number.setModel(limit_model);

            main_panel.repaint();
            main_panel.revalidate();
            set_select_table_name();
        } else
            JOptionPane.showMessageDialog(null, "Login information is not correct, please try again");
    }
    public void set_select_table_name()
    {
        select_table_name.removeAllItems();
        int count = connc.get_table_name();
        this.table_name = connc.table_name;
        for (int i = 0; i < count; i++)
        {
            select_table_name.addItem(table_name[i]);
        }
    }

    public void select_result()
    {
        String sql = "select ";
        if ((String)select_column_name_1.getSelectedItem() == "*")
            sql += "* ";
        else {
            for (int i = 0; i < select_column_current; i++) {
                sql += (String) select_column_name[i].getSelectedItem();
                if (select_column_current != i + 1)
                    sql += ", ";
                else
                    sql += " ";
            }
        }

        sql += "FROM " + select_table_name.getSelectedItem() + " ";
        String condition = select_condition.getText().trim();
        if (!condition.equals(""))
        {
            sql += "where " + select_condition.getText();
        }
        System.out.println(sql);
        simple_reult = connc.command(sql) + "\n";
    }

    private void output() {
        // command line input
        String SQL_code = text_command.getText();
        String return_result = connc.command(SQL_code);
        System.out.println(return_result);
        text_output.append(return_result + "\n");
    }
}
