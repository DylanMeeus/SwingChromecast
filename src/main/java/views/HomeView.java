package views;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pm.PM;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylan on 18.01.18.
 */
public class HomeView implements View {

    private JPanel mainPanel;

    public HomeView(){
        setupGui();
    }

    private void setupGui(){
        mainPanel = new JPanel(new BorderLayout());
        ChromeListView chromecastsView = new ChromeListView();
        mainPanel.add(chromecastsView.getGui(), BorderLayout.WEST);
        DetailView detailView = new DetailView();
        mainPanel.add(detailView.getGui(), BorderLayout.CENTER);
    }



    @NotNull
    @Override
    public JComponent getGui() {
        return mainPanel;
    }

    @Nullable
    @Override
    public PM getPM() {
        return null;
    }
}
