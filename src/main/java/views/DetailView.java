package views;

import org.jetbrains.annotations.NotNull;
import pm.DetailPM;
import pm.PM;

import javax.swing.*;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailView implements View{

    private JPanel mainPanel;
    private PM pm = new DetailPM();

    public DetailView(){
        setupGui();
    }

    private void setupGui(){
        mainPanel = new JPanel();
        mainPanel.add(new JLabel("Test"));
    }

    @NotNull
    @Override
    public JComponent getGui() {
        return mainPanel;
    }

    @Override
    public PM getPM(){
        return pm;
    }
}
