package services;

import su.litvak.chromecast.api.v2.ChromeCast;

import java.util.List;

/**
 * Created by dylan on 15.01.18.
 *
 * For testing purposes
 */
public class MockChromecastService implements ChromecastService {

    @Override
    public List<ChromeCast> getChromecasts() {
        return null;
    }

    @Override
    public void startDiscovery() {

    }

    @Override
    public void restartDiscovery() {

    }

    @Override
    public void stopDiscovery() {

    }
}
