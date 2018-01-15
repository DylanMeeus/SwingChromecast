package services;

import su.litvak.chromecast.api.v2.ChromeCast;

import java.util.List;

/**
 * Created by dylan on 14.01.18.
 */
public interface ChromecastService{

    public List<ChromeCast> getChromecasts();

    public void startDiscovery();

    public void restartDiscovery();

    public void stopDiscovery();

}
