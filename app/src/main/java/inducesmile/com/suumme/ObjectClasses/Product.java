package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 09/08/2017.
 */
public class Product {
    @SerializedName("id")
    int idOfOrder;

    @SerializedName("name")
    String name;

    public int getIdOfOrder() {
        return idOfOrder;
    }

    public void setIdOfOrder(int idOfOrder) {
        this.idOfOrder = idOfOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
