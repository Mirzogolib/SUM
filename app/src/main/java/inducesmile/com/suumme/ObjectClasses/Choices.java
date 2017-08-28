package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

public class Choices {
    @SerializedName("id")
    int idOfProduct;

    @SerializedName("name")
    String nameOfProduct;

    public int getIdOfProduct() {
        return idOfProduct;
    }

    public void setIdOfProduct(int idOfProduct) {
        this.idOfProduct = idOfProduct;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }
}
