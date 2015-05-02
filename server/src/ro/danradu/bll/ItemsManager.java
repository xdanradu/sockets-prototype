package ro.danradu.bll;
import ro.danradu.dal.ItemsCommand;
import ro.danradu.dto.Item;
import ro.danradu.dto.Items;

public class ItemsManager {
	private ItemsCommand itemsCommand;
	
	public ItemsManager(){
		itemsCommand = new ItemsCommand();
	}
	
	public Item insertItem(Item item) {		
		Item newItem = itemsCommand.insertItem(item);
		System.out.println("Save item: "+item.getName());
		return newItem;
	}

	public Item getItemById(int id) {
		Item item = itemsCommand.getItemById(id);
		return item;
	}

	public Items getItems() {
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getItems();
		return items;
	}
}
