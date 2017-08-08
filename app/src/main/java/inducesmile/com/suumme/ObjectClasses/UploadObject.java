package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 03/08/2017.
 */
public class UploadObject {


    @SerializedName("id")
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
