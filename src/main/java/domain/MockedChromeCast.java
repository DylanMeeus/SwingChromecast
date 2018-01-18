package domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.litvak.chromecast.api.v2.ChromeCast;

import java.nio.channels.Channel;

/**
 * Created by dylan on 15.01.18.
 * This is a mock for the chromecast to be used for UI testing.
 * Thus it's not a mock of the services, but rather it's a mock containing the same fields/data
 */
public class MockedChromeCast extends ChromeCast{

    private final String address;
    private final String name;
    private final String appsURL;
    private final String application;
    private final String title;
    private final String appTitle;
    private final String model;
    private final Channel channel;
    private final boolean autoreconnect;
    private final int port;
    private MockedChromeCast(@NotNull String address,
                             @NotNull String name,
                             @NotNull String appsURL,
                             @NotNull String application,
                             @NotNull String title,
                             @NotNull String appTitle,
                             @NotNull String model,
                             @Nullable Channel channel,
                             boolean autoreconnect,
                             int port
                             ) {
        super(address);
        this.address = address;
        this.name = name;
        this.appsURL = appsURL;
        this.application = application;
        this.appTitle = appTitle;
        this.title = title;
        this.model = model;
        this.channel = channel;
        this.autoreconnect = autoreconnect;
        this.port = port;
    }


    public static class MockedChromeCastBuilder{

        private String address;
        private String name;
        private String appsURL = "apps";
        private String application = "netflix";
        private String title;
        private String appTitle = "netflix";
        private String model;
        private Channel channel; // just leave null
        private boolean autoreconnect = false;
        private int port = 8001;

        public MockedChromeCastBuilder address(String address){
            this.address = address;
            return this;
        }

        public MockedChromeCastBuilder name(String name){
            this.name = name;
            return this;
        }

        public MockedChromeCastBuilder appsURL(String appsURL){
            this.appsURL = appsURL;
            return this;
        }

        public MockedChromeCastBuilder application(String application){
            this.application = application;
            return this;
        }

        public MockedChromeCastBuilder title(String title){
            this.title = title;
            return this;
        }

        public MockedChromeCastBuilder appTitle(String appTitle){
            this.appTitle = appTitle;
            return this;
        }

        public MockedChromeCastBuilder model(String model){
            this.model = model;
            return this;
        }

        public MockedChromeCastBuilder channel(Channel channel){
            this.channel = channel;
            return this;
        }

        public MockedChromeCastBuilder autoreconnect(boolean autoreconnect){
            this.autoreconnect = autoreconnect;
            return this;
        }

        public MockedChromeCastBuilder port(int port){
            this.port = port;
            return this;
        }

        public MockedChromeCast build(){
            return new MockedChromeCast(address, name, appsURL, application, title, appTitle, model, channel, autoreconnect, port);
        }

    }
}
