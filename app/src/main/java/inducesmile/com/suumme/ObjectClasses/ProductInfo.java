package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 18/07/2017.
 */

public class ProductInfo {

    @SerializedName("results")
    List<ResultsProd> results;

    public List<ResultsProd> getResults() {
        return results;
    }

    public void setResults(List<ResultsProd> results) {
        this.results = results;
    }

}


