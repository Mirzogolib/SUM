package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;


public class Order {
    @SerializedName("id")
    int idOfOrder;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("total_price")
    private String totalPrice;

    @SerializedName("price")
    private String price;

    @SerializedName("due_date")
    private String dueDate;

    @SerializedName("product")
    private Product product;

    @SerializedName("user")
    private OrderUserInfo user;





    public int getIdOfOrder() {
        return idOfOrder;
    }

    public void setIdOfOrder(int idOfOrder) {
        this.idOfOrder = idOfOrder;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderUserInfo getUser() {
        return user;
    }

    public void setUser(OrderUserInfo user) {
        this.user = user;
    }
}
