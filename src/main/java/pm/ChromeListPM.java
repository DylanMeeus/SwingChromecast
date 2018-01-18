package pm;

import beans.ChromeListBean;
import com.jgoodies.binding.beans.Model;

/**
 * Created by dylan on 18.01.18.
 */
public class ChromeListPM implements PM {
    private Model bean = new ChromeListBean();

    @Override
    public Model getBean() {
        return bean;
    }
}
