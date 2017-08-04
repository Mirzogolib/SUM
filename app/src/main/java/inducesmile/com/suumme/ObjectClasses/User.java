package inducesmile.com.suumme.ObjectClasses;

import com.google.gson.annotations.SerializedName;


public class User {
    @SerializedName ("id")
    public int id;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("middle_name")
    String middle_name;
    @SerializedName("address")
    public String address;
    @SerializedName("location")
    String location;
    @SerializedName("user_type")
    String  user_type;
    @SerializedName("region")
    public String region;
    @SerializedName("name")
   public String name;
    @SerializedName("phone_number")
    String phone_number;
    @SerializedName("email")
    String email;
    @SerializedName("photo")
    String photo;




    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getAddress() {
        return address;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getLocation() {
        return location;
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
