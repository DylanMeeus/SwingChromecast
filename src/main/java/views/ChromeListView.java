package views;

import beans.ChromeListBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pm.ChromeListPM;
import pm.PM;
import services.DefaultChromecastService;
import services.MockChromecastService;
import su.litvak.chromecast.api.v2.ChromeCast;
import views.components.ChromecastCellRenderer;

import com.jgoodies.binding.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created by dylan on 14.01.18.
 */
public class ChromeListView implements View {

    private JPanel mainPanel;
    private JList<ChromeCast> chromeCastList;
    private PM chromeListPM = new ChromeListPM();

    public ChromeListView(){
        setupGui();
        setupListeners();
    }

    private void setupGui(){
        mainPanel = new JPanel();
        chromeCastList = new JList<ChromeCast>();
        chromeCastList.setCellRenderer(new ChromecastCellRenderer());
        mainPanel.add(chromeCastList);
    }

    private void setupListeners(){
        chromeListPM.getBean().addPropertyChangeListener(ChromeListBean.CHROMECAST_PROPERTY, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                Object newValue = propertyChangeEvent.getNewValue();
                if (!(newValue instanceof List)) {
                    return;
                }
                List<ChromeCast> chromeCasts = (List<ChromeCast>) newValue;
                chromeCastList.setModel(new DefaultListModel<ChromeCast>(){{
                    chromeCasts.forEach(cc -> addElement(cc));}});
            }
        });

    }

    private void populateJList(List<ChromeCast> chromecasts) {
        final DefaultListModel<ChromeCast> listModel = new DefaultListModel<ChromeCast>();
        System.out.println("chromecasts: " + chromecasts.size());
        chromecasts.forEach(c -> listModel.addElement(c));
        chromeCastList.setModel(listModel);
        mainPanel.revalidate();
    }






    @Override
    @NotNull
    public JComponent getGui() {
        return mainPanel;
    }

    @Nullable
    @Override
    public PM getPM() {
        return chromeListPM;
    }
}
