package beans;

import com.jgoodies.binding.beans.Model;
import su.litvak.chromecast.api.v2.ChromeCast;
import su.litvak.chromecast.api.v2.ChromeCastSpontaneousEvent;
import su.litvak.chromecast.api.v2.ChromeCastSpontaneousEventListener;
import su.litvak.chromecast.api.v2.Status;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailBean extends Model {

    // the real reference
    private ChromeCast chromeCast;
    public static String CHROMECAST = "chromeCast";

    private Status castStatus;
    public static String CAST_STATUS = "chromecastStatus";

    private Status prevStatus;


    public DetailBean(){
        // set initial values
        TimerTask updateChromeCastTask = new TimerTask(){

            @Override
            public void run() {
                //System.out.println("Updating the chromecast?");
                try {
                    Status status = chromeCast.getStatus();
                    if (status == null) {
                        return;
                    }
                    if (!status.equals(prevStatus)) {
                        setCastStatus(status);
                    }
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("updateChromecastTask");
        timer.scheduleAtFixedRate(updateChromeCastTask, 0, 5000); // update every 5 seconds
    }

    public void setChromeCast(ChromeCast chromeCast) {
        ChromeCast oldValue = this.chromeCast;
        this.chromeCast = chromeCast;
        firePropertyChange(CHROMECAST, oldValue, this.chromeCast);
    }

    public void setCastStatus(Status status) {
        Status oldStatus = this.castStatus;
        this.castStatus = status;
        firePropertyChange(CAST_STATUS, oldStatus, status);
    }

    public ChromeCast getChromeCast(){
        return chromeCast;
    }

}
