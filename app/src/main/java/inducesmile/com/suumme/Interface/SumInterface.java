package inducesmile.com.suumme.Interface;

import java.util.List;

import inducesmile.com.suumme.ObjectClasses.AllUsers;
import inducesmile.com.suumme.ObjectClasses.ChoicesAll;
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
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface SumInterface {
    //  AUTHENTICATION
    @POST("api/v1/authentication/")
    Call<Token> loginUser(@Body UserInfo userInfo);

    //  USER
    @GET("api/v1/user/auth/")
    Call<User> getDetail(@Header("Authorization") String token);

    @PUT("api/v1/user/{id}/")
    Call<User> update(@Header("Authorization") String token, @Path("id") int id, @Body User user);

    @PATCH("api/v1/user/{id}/")
    Call<User> updatePatch(@Header("Authorization") String token, @Path("id") int id, @Body User user);

    @GET("api/v1/user")
    Call<AllUsers> getUsers(@Header("Authorization") String token);

    @GET("api/v1/user/{id}")
    Call<User> getUser(@Header("Authorization") String token, @Path("id") int id);


    //  PRODUCT
    @GET("api/v1/product/")
    Call<ProductInfo> getProduct(@Header("Authorization") String token);

    @GET("api/v1/product/")
    Call<ProductInfo> getProductCompany(@Header("Authorization") String token, @Query("user") int id); // ?user=id

    @GET("api/v1/product/{id}")
    Call<ResultsProd> getProductById(@Header("Authorization") String token, @Path("id") int id);

    @POST("api/v1/product/")
    Call<ResultsProd> createProduct(@Header("Authorization") String token);


    // ORDER
    @GET("api/v1/order")
    Call<OrderCompany> getOrder(@Header("Authorization") String token);

    @POST("api/v1/order")
    Call<Order> createOrder(@Header("Authorization") String token);

    @GET("api/v1/order/{id}")
    Call<Order> getOrderById(@Header("Authorization") String token, @Path("id") int id);


    //   FILE
    @Multipart
    @POST("api/v1/file/")
    Call<UploadObject> uploadFile(@Header("Authorization") String token, @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("api/v1/choices/choices/")
    Call<ChoicesAll> getAllChoices(@Header("Authorization") String token, @Field("key") List<String> array);
}
