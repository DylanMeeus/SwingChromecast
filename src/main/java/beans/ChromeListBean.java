package beans;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import services.DefaultChromecastService;
import su.litvak.chromecast.api.v2.ChromeCast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dylan on 18.01.18.
 * The bean representing the list of chromecasts
 */
public class ChromeListBean extends Model{

    private SelectionInList chromeCastList = new SelectionInList();
    private Timer timer;
    private ChromeCast selectedChromeCast;
    public static final String LIST_SELECTION = "chromeListSelection";
    public ChromeListBean(){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                List<ChromeCast> chromecasts = DefaultChromecastService.getChromecastService().getChromecasts();
                // once we have found some chromecasts, stop reloading and let the user handle refreshes?
                if (!chromecasts.isEmpty()) {
                    timer.cancel();
                    chromeCastList.setList(chromecasts);
                }
            }
        };
        timer = new Timer("serviceTimer");
        timer.scheduleAtFixedRate(task, 0, 5000);

        chromeCastList.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                ChromeCast oldChromeCast = selectedChromeCast;
                selectedChromeCast = (ChromeCast) chromeCastList.getSelection();
                firePropertyChange(LIST_SELECTION, oldChromeCast, selectedChromeCast);
            }
        });
    }

    public SelectionInList getChromecastList() {
        return chromeCastList;
    }

}
