package beans;

import com.jgoodies.binding.beans.Model;
import su.litvak.chromecast.api.v2.ChromeCast;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailBean extends Model {

    // the real reference
    private ChromeCast chromeCast;
    public static String CHROMECAST = "chromeCast";

    public DetailBean(){
        // set initial values
    }

    public void setChromeCast(ChromeCast chromeCast) {
        ChromeCast oldValue = this.chromeCast;
        this.chromeCast = chromeCast;
        firePropertyChange(CHROMECAST, oldValue, this.chromeCast);
    }

}
