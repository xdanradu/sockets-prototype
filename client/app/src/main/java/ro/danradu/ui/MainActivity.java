package ro.danradu.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ro.danradu.client.R;
import ro.danradu.dto.*;
import ro.danradu.networking.Connection;


public class MainActivity extends Activity {

    private Button startButton, sendButton, stopButton;
    private TextView msg;

    private MapView mapView;
    private MapController mapController;
    private Connection net;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream=null;

    private MyResponse response=null;
    private MyRequest request = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        net = Connection.getInstance();//get singleton, this object is unique per application

        mapView = (MapView) this.findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapController = (MapController) this.mapView.getController();
        mapController.setZoom(10);

        GeoPoint center=new GeoPoint(46.148831, 25.005521);
        mapController.animateTo(center);

        msg = (TextView) findViewById(R.id.output);

        startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataTransmission().execute("OPEN");
            }
        });

        sendButton = (Button) findViewById(R.id.sendBtn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataTransmission().execute("SEND");
            }
        });

        stopButton = (Button) findViewById(R.id.stopBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataTransmission().execute("CLOSE");
            }
        });
    }

    private class DataTransmission extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String message ="";

            if (params[0]=="OPEN") {
                try { // The localhost from Android emulator is 10.0.2.2
                        outputStream = new ObjectOutputStream(net.getSocket().getOutputStream());
                        inputStream = new ObjectInputStream(net.getSocket().getInputStream());
                    message = "CONNECTED";
                    }
                    catch (Exception e) {
                        e.printStackTrace(); }
            }

            if (params[0]=="SEND"){
                try {
                    //MyRequest request = new MyRequest("GetItemById", new MyData(1,""));
                    request = new MyRequest("GetItems", new MyData(0,""));
                    //MyRequest request = new MyRequest("InsertItem", new Item(0,"TST1", "12", "25"));
                    outputStream.writeObject(request);
                    outputStream.flush();
                    response = (MyResponse) inputStream.readObject();
                    message="RESPONSE_OK";
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (params[0]=="CLOSE") {
                try {
                    request = new MyRequest("StopConnection", null);
                    outputStream.writeObject(request);
                    outputStream.flush();
                    outputStream.close();
                    net.getSocket().close();
                    message="CONNECTION_CLOSED";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("CONNECTED")) {
                msg.setText("Connected to server"); // do something with the response data (update ui)
            }

            if (result.equals("RESPONSE_OK")) {
                msg.setText(response.toString()); // do something with the response data (update ui)
            }

            if (result.equals("CONNECTION_CLOSED")){
                finish();
                System.exit(0);
            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
