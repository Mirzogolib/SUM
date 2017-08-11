package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;



public class UserProduct {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    private String name;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
