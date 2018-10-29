package P1;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        // start GUI
        JFrame main_frame = new JFrame("NOOB SQL");
        main_frame.setContentPane(new GUI().main_panel);
        main_frame.setPreferredSize(new Dimension(1280,720));
        main_frame.setMinimumSize(new Dimension(1280,720));
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.pack();
        main_frame.setVisible(true);
    }

}
