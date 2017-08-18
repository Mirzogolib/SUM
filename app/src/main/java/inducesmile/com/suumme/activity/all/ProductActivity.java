package inducesmile.com.suumme.activity.all;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;


public class ProductActivity extends AppCompatActivity {
    APIService apiService;
    String token;
    TextView productName, productPrice;
    String TAG = "test";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_order_sample_list);

        apiService = new APIService();
        Intent intent = getIntent();
        token = intent.getExtras().getString("token");

        productName = (TextView) findViewById(R.id.product_name);
        productPrice = (TextView) findViewById(R.id.product_price);



//        apiService.getProduct(token).enqueue(new Callback<LProductInfo>() {
//            @Override
//            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
//               Log.d(TAG, "Success in geting product");
//
//                productName.setText(response.body().getResults().get(0).getNameOfProduct());
//
//                productPrice.setText(response.body().getResults().get(0).getPrice()+" Sum");
//
//            }
//
//            @Override
//            public void onFailure(Call<ProductInfo> call, Throwable t) {
//                Log.d(TAG, t.getMessage());
//                Log.d(TAG, "can not do :(");
//            }
//        });








    }
}
