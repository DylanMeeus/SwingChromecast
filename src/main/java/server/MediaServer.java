package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by dylan on 25.01.18.
 * Media server from which we can stream videos to the chromecast (or other sources?)
 */
public class MediaServer {


    private static File movieFile;
    private static final MediaServer SERVER = new MediaServer();
    private MediaServer(){
        try {
            setupServer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static MediaServer getMediaServer(){
        return SERVER;
    }

    public void setupServer() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("192.168.1.57", 8000),8000);
        httpServer.createContext("/moviefile", new MovieHandler());
        httpServer.setExecutor(null);
        httpServer.start();
    }

    public void setMovieFile(File file){
        movieFile = file;
    }

    static class MovieHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            httpExchange.sendResponseHeaders(200, movieFile.length());
            httpExchange.getResponseHeaders().put("Content-Type", Arrays.asList(new String[]{"video/mp4"}));
            OutputStream os = httpExchange.getResponseBody();
            byte[] buffer = new byte[1024];
            FileInputStream fs = new FileInputStream(movieFile);
            int count = 0;
            while ((count = fs.read(buffer)) >= 0) {
                os.write(buffer, 0, count);
            }
            os.flush();
            fs.close();
            os.close();
        }
    }
}
