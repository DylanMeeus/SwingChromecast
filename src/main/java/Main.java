import server.MediaServer;
import views.ChromeListView;
import views.HomeView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylan on 14.01.18.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Setting up server");
        MediaServer s = MediaServer.getMediaServer();
        System.out.println("server set up!");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));
        frame.setContentPane(new HomeView().getGui());
        frame.setVisible(true);
    }
}
