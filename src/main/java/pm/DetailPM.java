package pm;

import beans.DetailBean;
import com.jgoodies.binding.beans.Model;
import su.litvak.chromecast.api.v2.ChromeCast;

/**
 * Created by dylan on 18.01.18.
 */
public class DetailPM implements PM{

    private DetailBean bean = new DetailBean();

    public void setChromecast(ChromeCast cast) {
        bean.setChromeCast(cast);
    }

    public ChromeCast getCurrentChromeCast(){
        return bean.getChromeCast();
    }

    @Override
    public DetailBean getBean() {
        return bean;
    }
}
