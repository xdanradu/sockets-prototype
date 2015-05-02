package ro.danradu.dto;

public class Item implements DataPayload {

    private static final long serialVersionUID = 7950169519310163575L;
    private int id;
    private String name;
    private String lat;
    private String lon;

    public Item(){}
    
    public Item(int id, String name, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon=lon;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int hashCode() {
        return id;
    }

    public String toString() {
        return "Id = " + getId() + " ; Name = " + getName()+" ; lat: "+lat+" lon: "+lon;
    }
}