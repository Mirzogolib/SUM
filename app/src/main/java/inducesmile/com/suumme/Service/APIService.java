package inducesmile.com.suumme.Service;

import inducesmile.com.suumme.Interface.SumInterface;
import inducesmile.com.suumme.ObjectClasses.AllUsers;
import inducesmile.com.suumme.ObjectClasses.Order;
import inducesmile.com.suumme.ObjectClasses.OrderCompany;
import inducesmile.com.suumme.ObjectClasses.ProductInfo;
import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.ObjectClasses.UploadObject;
import inducesmile.com.suumme.ObjectClasses.User;
import inducesmile.com.suumme.ObjectClasses.UserInfo;
import inducesmile.com.suumme.Token.Token;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIService {
    String TAG;

    static String BASE_URL = "http://suum.me/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public Call<Token> postUser(UserInfo userInfo) {
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.loginUser(userInfo);
    }

    public Call<User> getDetail(String token){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getDetail(token);
    }
    public Call<User> update(String token, int id, User user){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.update(token, id, user);
    }
    public Call<ProductInfo> getProduct(String token ){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getProduct(token);
    }

    public Call<ResultsProd> getProductById(String token, int id ){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getProductById(token, id);
    }


    public Call<User> getUser(String token ,int id){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getUser(token, id);
    }


    public Call<AllUsers> getUsers(String token){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getUsers(token);
    }

    public Call<OrderCompany> getOrder(String token ){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getOrder(token);
    }

    public Call<Order> getOrderById(String token , int id){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.getOrderById(token, id);
    }

    public Call<UploadObject> uploadFile(String token, MultipartBody.Part file){
        SumInterface apiInterface = getClient().create(SumInterface.class);
        return apiInterface.uploadFile(token, file);
    }

}
