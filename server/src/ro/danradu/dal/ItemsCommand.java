package ro.danradu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ro.danradu.dto.Item;

public class ItemsCommand{

	MysqlDatabase mysqlDatabase = new MysqlDatabase();

	public ArrayList<Item> getItems() {
		System.out.println("Connect to the database and read all the items");
		
		ArrayList<Item> items = new ArrayList<Item>();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT * FROM items";
		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				Item item = new Item(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));			
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();
		return items;
	}

	public Item getItemById(int id) {
		System.out.println("Connect to the database and read the item id="+id);
		
		Item item = new Item();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT * FROM items WHERE id="+id;
		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				item = new Item(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();
		System.out.println(item.toString());
		return item;
	}

	public Item insertItem(Item item) {
		System.out.println("Connect to the database and insert a new item");
		
		Item newItem=new Item();
		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "INSERT INTO items (name, lat, lon) VALUES ('"+item.getName()+"','"+item.getLat()+"','"+item.getLon()+"')";
		Statement stat = mysqlDatabase.getStatement();

		try {
			int result = stat.executeUpdate(mySQLQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mySQLQuery = "SELECT * FROM items WHERE name='"+item.getName()+"'";
		try {
			ResultSet result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				newItem = new Item(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();	
		System.out.println("NIT: "+newItem.toString());
		return newItem;
	}
}

