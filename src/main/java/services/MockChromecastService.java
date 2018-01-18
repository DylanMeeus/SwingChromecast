package services;

import domain.MockedChromeCast;
import su.litvak.chromecast.api.v2.ChromeCast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylan on 15.01.18.
 *
 * For testing purposes
 */
public class MockChromecastService implements ChromecastService {

    @Override
    public List<ChromeCast> getChromecasts() {
        List<ChromeCast> casts = new ArrayList<>(5);
        ChromeCast cast1 = new MockedChromeCast.MockedChromeCastBuilder()
                .address("192.1.1.2")
                .name("chromecast-1")
                .port(8001)
                .model("Chromecast")
                .title("Family Home")
                .build();
        casts.add(cast1);
        return casts;
    }

    public static MockChromecastService getChromecastService(){
        return new MockChromecastService();
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
