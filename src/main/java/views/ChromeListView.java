package views;

import org.jetbrains.annotations.NotNull;
import services.DefaultChromecastService;
import services.MockChromecastService;
import su.litvak.chromecast.api.v2.ChromeCast;
import views.components.ChromecastCellRenderer;

import javax.swing.*;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created by dylan on 14.01.18.
 */
public class HomeView implements View{

    private JPanel mainPanel;
    private JList<ChromeCast> chromeCastList;

    private Timer timer;
    public HomeView(){
        setupGui();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                List<ChromeCast> chromecasts = DefaultChromecastService.getChromecastService().getChromecasts();
                // once we have found some chromecasts, stop reloading and let the user handle refreshes?
                if (!chromecasts.isEmpty()) {
                    populateJList(chromecasts);
                    timer.cancel();
                }
            }
        };
        timer = new Timer("serviceTimer");
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    private void setupGui(){
        mainPanel = new JPanel();
        chromeCastList = new JList<ChromeCast>();
        chromeCastList.setCellRenderer(new ChromecastCellRenderer());
        mainPanel.add(chromeCastList);
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
}
