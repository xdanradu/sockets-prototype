package ro.danradu.dto;

import java.io.Serializable;

/**
 * Created by Dan on 4/30/2015.
 */
public class MyResponse implements Serializable {
    public String status;
    public DataPayload object;

    public MyResponse(String status, DataPayload object){
    	this.status=status;
    	this.object=object;
    }
    
    public String toString(){
        return "method: "+status+" dataObject: "+object.toString();
    }
}
