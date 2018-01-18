package services;

import org.jetbrains.annotations.NotNull;
import su.litvak.chromecast.api.v2.ChromeCast;
import su.litvak.chromecast.api.v2.ChromeCasts;

import java.io.IOException;
import java.util.List;

/**
 * Created by dylan on 14.01.18.
 */
public class DefaultChromecastService implements ChromecastService{


    private static DefaultChromecastService service;
    private DefaultChromecastService(){
        startDiscovery();
    }

    public static DefaultChromecastService getChromecastService(){
        if (service == null) {
            service = new DefaultChromecastService();
        }
        return service;
    }

    @Override
    @NotNull
    public List<ChromeCast> getChromecasts(){
        return ChromeCasts.get();
    }

    @Override
    public void startDiscovery() {
        // perform a discovery on startup
        try {
            ChromeCasts.startDiscovery();
        } catch (IOException e) {
            throw new RuntimeException("No chromecasts found!");
        }
    }

    @Override
    public void restartDiscovery() {

    }

    @Override
    public void stopDiscovery() {

    }




}
