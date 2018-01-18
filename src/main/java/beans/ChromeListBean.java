package beans;

import com.jgoodies.binding.beans.Model;
import org.jetbrains.annotations.NotNull;
import services.DefaultChromecastService;
import su.litvak.chromecast.api.v2.ChromeCast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dylan on 18.01.18.
 * The bean representing the list of chromecasts
 */
public class ChromeListBean extends Model{

    private List<ChromeCast> chromeCasts;
    public static final String CHROMECAST_PROPERTY = "chromeCasts";
    private Timer timer;
    public ChromeListBean(){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                List<ChromeCast> chromecasts = DefaultChromecastService.getChromecastService().getChromecasts();
                // once we have found some chromecasts, stop reloading and let the user handle refreshes?
                if (!chromecasts.isEmpty()) {
                    timer.cancel();
                    setChromeCasts(chromecasts);
                }
            }
        };
        timer = new Timer("serviceTimer");
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    public void setChromeCasts(@NotNull List<ChromeCast> casts) {
        List<ChromeCast> oldValue = new ArrayList<ChromeCast>(){{
            if (chromeCasts != null) {
                addAll(chromeCasts);
            }
        }};
        chromeCasts = casts;
        firePropertyChange(CHROMECAST_PROPERTY, oldValue, chromeCasts);
    }

}
