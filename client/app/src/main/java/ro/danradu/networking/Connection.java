package ro.danradu.networking;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Dan on 4/28/2015.
 */
/*Should be a singleton*/
public class Connection {
    private Socket socket = null;
    private String API_ADDRESS="10.0.2.2";
    private int API_PORT=2000;

    private static Connection instance = null;
    protected Connection() {
        // Exists only to defeat instantiation.
    }
    public static Connection getInstance() {
        if(instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public Socket getSocket(){
        if (this.socket==null){
            try {
                this.socket= new Socket(API_ADDRESS, API_PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.socket;
    }
}
