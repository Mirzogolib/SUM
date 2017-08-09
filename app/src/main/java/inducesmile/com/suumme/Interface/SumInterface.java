package inducesmile.com.suumme.Interface;

import inducesmile.com.suumme.ObjectClasses.AllUsers;
import inducesmile.com.suumme.ObjectClasses.ProductInfo;
import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.ObjectClasses.UploadObject;
import inducesmile.com.suumme.ObjectClasses.User;
import inducesmile.com.suumme.ObjectClasses.UserInfo;
import inducesmile.com.suumme.Token.Token;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface SumInterface {
    @POST("api/v1/authentication/")
    Call<Token> loginUser(@Body UserInfo userInfo);


    @GET("api/v1/user/auth/")
    Call<User> getDetail(@Header("Authorization") String token);

    @PUT("api/v1/user/{id}/")
    Call<User> update(@Header("Authorization") String token, @Path("id") int id, @Body User user);

    @GET("api/v1/product/")
    Call<ProductInfo> getProduct(@Header("Authorization") String token);

    @GET("api/v1/product/{id}")
    Call<ResultsProd> getProductById(@Header("Authorization") String token, @Path("id") int id );

    @GET("api/v1/user")
    Call<AllUsers> getUsers(@Header("Authorization") String token);



    @GET("api/v1/user/{id}")
    Call<User> getUser(@Header("Authorization") String token,  @Path("id") int id);

    @Multipart
    @POST("api/v1/file/")
    Call<UploadObject> uploadFile(@Header("Authorization") String token, @Part MultipartBody.Part file);

}
