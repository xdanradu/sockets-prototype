package ro.danradu.dto;

import java.util.ArrayList;

public class Items implements DataPayload{
    public ArrayList<Item> arr;

    public Items(){}
    
    public Items(ArrayList<Item> array) {
        this.arr=arr;
    }

    @Override
    public int getId() {
        return 1;
    }

   

    public String toString() {
        return "Array size: "+arr.size();
    }
}
