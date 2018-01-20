package views;

import beans.ChromeListBean;
import com.jgoodies.binding.adapter.Bindings;
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
        Bindings.bind(chromeCastList, ((ChromeListBean)getPM().getBean()).getChromecastList());
    }

    private void populateJList(List<ChromeCast> chromecasts) {
        System.out.println(String.format("Found %d chromecasts", chromecasts.size()));
        final DefaultListModel<ChromeCast> listModel = new DefaultListModel<ChromeCast>();
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
