package pm;

import beans.DetailBean;
import com.jgoodies.binding.beans.Model;
import domain.ChromeCastApp;
import server.MediaServer;
import su.litvak.chromecast.api.v2.ChromeCast;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

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

    public void playMovieFromFile(File movieFile) {
        MediaServer mediaServer = MediaServer.getMediaServer();
        mediaServer.setMovieFile(movieFile);
        // play this on the chromecast..

        ChromeCast chromeCast= getCurrentChromeCast();
        if (chromeCast == null) {
            return;
        }
        try {
            chromeCast.connect();
            chromeCast.launchApp(ChromeCastApp.DEFAULT_MEDIA_PLAYER.getApp());
            chromeCast.load(movieFile.getAbsolutePath(),           // Media title
                    "",  // URL to thumbnail based on media URL
                    "http://192.168.1.57:8000/moviefile.mp4",
                    null // media content type (optional, will be discovered automatically)
            );
            chromeCast.play();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

}
