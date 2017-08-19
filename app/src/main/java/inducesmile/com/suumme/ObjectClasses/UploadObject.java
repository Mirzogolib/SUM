package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;

public class UploadObject {


    @SerializedName("id")
    public int id;

    @SerializedName("file")
    public String file;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
