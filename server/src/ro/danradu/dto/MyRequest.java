package ro.danradu.dto;

import java.io.Serializable;

/**
 * Created by Dan on 4/30/2015.
 */
public class MyRequest implements Serializable {
    public String method;
    public DataPayload dataObject;//this is the serializable interface

    public MyRequest(String m, DataPayload object){
        this.method = m;
        this.dataObject = object;
    }

    public String toString(){
        return "method: "+method+" dataObject: "+dataObject.toString();
    }

}
