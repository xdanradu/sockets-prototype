package ro.danradu.dto;

/**
 * Created by Dan on 4/30/2015.
 */
public class MyData implements DataPayload{
    int id;
    String value;

    public MyData(int id, String value){
        this.id = id;
        this.value = value;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
