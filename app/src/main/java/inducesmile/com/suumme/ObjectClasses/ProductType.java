package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductType {
    @SerializedName("id")
    int idOfProduct;

    @SerializedName("name")
    String nameOfProduct;

    @SerializedName("choices")
    List<Choices> choices;

    public int getIdOfProduct() {
        return idOfProduct;
    }

    public void setIdOfProduct(int idOfProduct) {
        this.idOfProduct = idOfProduct;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }
}
