package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 09/08/2017.
 */

public class UserInfoProduct {

    @SerializedName("id")
    String idOfCompany;

    @SerializedName("name")
    String companyName;


    public String getIdOfCompany() {
        return idOfCompany;
    }

    public void setIdOfCompany(String idOfCompany) {
        this.idOfCompany = idOfCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
