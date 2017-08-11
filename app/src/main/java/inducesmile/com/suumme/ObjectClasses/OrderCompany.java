package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 09/08/2017.
 */

public class OrderCompany {
    @SerializedName("results")
    List<Order> results;

    public List<Order> getResults() {
        return results;
    }

    public void setResults(List<Order> results) {
        this.results = results;
    }
}
