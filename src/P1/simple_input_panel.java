package P1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simple_input_panel {
    public JPanel panel1;
    private JComboBox select_table_name;
    private JComboBox select_column_name;
    private JTextField select_condition;
    public String sql_code= null;
    private String selected_table = null;

    public void set_table_name(String[] name, int count)
    {
        String[] table_name = new String[count];
        table_name = name;

        select_table_name.removeAllItems();
        select_table_name.addItem("*");

        for (int i = 0; i < count; i++)
        {
            select_table_name.addItem(table_name[i]);
        }

        select_table_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected_table = (String)select_table_name.getSelectedItem();
            }
        });
    }

}
