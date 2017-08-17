package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 14/08/2017.
 */

public class OrderUserInfo {
    @SerializedName("id")
    int idOfUser;

    @SerializedName("name")
    private String userName;


    public int getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(int idOfUser) {
        this.idOfUser = idOfUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
