package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;


public class ResultsProd {
    @SerializedName("id")
    int idOfProduct;

    @SerializedName("name")
    String nameOfProduct;

    @SerializedName("price")
    String price;

    @SerializedName("user")
    UserInfoProduct userInfoProduct;

    @SerializedName("definition")
    String definition;


    public int getIdOfProduct() {
        return idOfProduct;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public String getPrice() {
        return price;
    }

    public UserInfoProduct getUserInfoProduct() {
        return userInfoProduct;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setUserInfoProduct(UserInfoProduct userInfoProduct) {
        this.userInfoProduct = userInfoProduct;
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
