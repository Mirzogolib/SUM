package inducesmile.com.suumme.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


@Table(name = "user_table")
public class UserDB extends Model {

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static UserDB getUser(){
        return new Select().from(UserDB.class).executeSingle();
    }


}
