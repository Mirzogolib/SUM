package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;


public class ResultsProd {
    @SerializedName("id")
    int idOfProduct;

    @SerializedName("name")
    String nameOfProduct;

    @SerializedName("price")
    String price;

    public int getIdOfProduct() {
        return idOfProduct;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setIdOfProduct(int idOfProduct) {
        this.idOfProduct = idOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
