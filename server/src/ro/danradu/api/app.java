package ro.danradu.api;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ro.danradu.dto.Item;
import ro.danradu.dto.Items;
import ro.danradu.dto.MyRequest;
import ro.danradu.dto.MyResponse;

public class app {

	public static final int PORT = 2000;

	void startServer() {
		ServerSocket ss = null;
		try {

			ss = new ServerSocket(PORT);
			System.out.println("Waiting for connections"); 
			while (true) {
				Socket socket = ss.accept();
				System.out.println("New client manager started"); 
				new ClientManagerThread(socket).start();
			}

		} catch (IOException ex) {
			System.err.println("Eroare :" + ex.getMessage());
		} finally {
			try {
				ss.close();
			} catch (IOException ex2) {
			}
		}
	}

	public static void main(String args[]) {
		app app = new app();
		app.startServer();
	}


}
