import views.ChromeListView;
import views.HomeView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylan on 14.01.18.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));
        frame.setContentPane(new HomeView().getGui());
        frame.setVisible(true);
    }
}
