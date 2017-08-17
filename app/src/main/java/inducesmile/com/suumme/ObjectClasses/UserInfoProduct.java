package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 09/08/2017.
 */

public class UserInfoProduct {

    @SerializedName("id")
    int idOfCompany;

    @SerializedName("name")
    String companyName;


    public int getIdOfCompany() {
        return idOfCompany;
    }

    public void setIdOfCompany(int idOfCompany) {
        this.idOfCompany = idOfCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
