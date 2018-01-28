package domain;

/**
 * Created by dylan on 28.01.18.
 */
public enum ChromeCastApp {

    DEFAULT_MEDIA_PLAYER("CC1AD845");


    private String app;
    private ChromeCastApp(String app){
        this.app = app;
    }

    public String getApp(){
        return app;
    }

}
