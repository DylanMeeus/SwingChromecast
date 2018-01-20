package views.components;

import net.miginfocom.swing.MigLayout;
import su.litvak.chromecast.api.v2.ChromeCast;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylan on 15.01.18.
 */
public class ChromecastCellRenderer extends JPanel implements ListCellRenderer<ChromeCast> {

    private static Color BABY_BLUE = new Color(137, 207, 240);

    @Override
    public Component getListCellRendererComponent(JList<? extends ChromeCast> list, ChromeCast chromeCast, int index, boolean isSelected, boolean cellFocussed) {
        Color unselectedColour = Color.WHITE;
        Color selectedColor = BABY_BLUE;

        this.setLayout(new MigLayout("debug"));

        if (isSelected) {
            this.setBackground(selectedColor);
        } else {
            this.setBackground(unselectedColour);
        }

        JLabel nameLabel = new JLabel(chromeCast.getTitle());
        this.add(nameLabel);

        return this;
    }
}
