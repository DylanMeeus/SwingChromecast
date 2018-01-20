package views;

import beans.ChromeListBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pm.PM;
import su.litvak.chromecast.api.v2.ChromeCast;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by dylan on 18.01.18.
 */
public class HomeView implements View {

    private JPanel mainPanel;
    private ChromeListView chromecastsView;
    private DetailView detailView;
    public HomeView(){
        setupGui();
        setupListeners();
    }

    private void setupGui(){
        mainPanel = new JPanel(new BorderLayout());
        chromecastsView = new ChromeListView();
        mainPanel.add(chromecastsView.getGui(), BorderLayout.WEST);
        detailView = new DetailView();
        mainPanel.add(detailView.getGui(), BorderLayout.CENTER);
    }

    private void setupListeners(){
        chromecastsView.getPM().getBean().addPropertyChangeListener(ChromeListBean.LIST_SELECTION, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                if (pce.getNewValue() == null) {
                    return;
                }
                ChromeCast selectedCast = (ChromeCast) pce.getNewValue();
                detailView.getPM().setChromecast(selectedCast);
            }
        });
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
