package ro.danradu.api;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ro.danradu.bll.ItemsManager;
import ro.danradu.dto.Item;
import ro.danradu.dto.Items;
import ro.danradu.dto.MyRequest;
import ro.danradu.dto.MyResponse;

public class ClientManagerThread extends Thread {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outputStream;
	private MyRequest request;

	ClientManagerThread(Socket socket) throws IOException {
		this.socket = socket;
		inStream = new ObjectInputStream(socket.getInputStream());
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		request=new MyRequest("", null);
	}

	public void run() {
		try {
			while (!request.method.equals("StopConnection")) {
				request = (MyRequest) inStream.readObject();
				if (!request.method.equals("StopConnection")) {
					System.out.println("REQUEST: " + request.toString());
					MyResponse response = manageRequest(request);
					outputStream.writeObject(response);
					outputStream.flush();
				} else {
					inStream.close();
					outputStream.close();
					socket.close();
					System.out.println("Server connection stopped");
				}
			} 
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}//end run

	private static MyResponse manageRequest(MyRequest req) {

		MyResponse response = null;
		ItemsManager itemsManager = new ItemsManager();

		if (req.method.equals("GetItemById")) {
			Item item = new Item();
			item = itemsManager.getItemById(req.dataObject.getId());
			response = new MyResponse("OK", item);
		}

		if (req.method.equals("GetItems")) {
			Items items = itemsManager.getItems();
			response = new MyResponse("OK", items);
		}

		if (req.method.equals("InsertItem")) {
			Item it = new Item();
			it = itemsManager.insertItem((Item) req.dataObject);
			response = new MyResponse("OK", it);
		}

		System.out.println(response.toString());
		return response;
	}
}
