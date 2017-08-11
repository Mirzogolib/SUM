package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductInfo {

    @SerializedName("results")
    private List<ResultsProd> results;







    public List<ResultsProd> getResults() {
        return results;
    }



    public void setResults(List<ResultsProd> results) {
        this.results = results;
    }
}


