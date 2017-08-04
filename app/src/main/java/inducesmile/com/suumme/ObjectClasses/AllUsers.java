package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 19/07/2017.
 */

public class AllUsers {
    @SerializedName("results")
    List<User> results;


    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
