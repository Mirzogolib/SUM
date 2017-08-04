package inducesmile.com.suumme.activity.shop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import inducesmile.com.suumme.ObjectClasses.ProductInfo;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.adapter.DataAdapter;


public class ShopProductList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ArrayList<ProductInfo> data;
    private DataAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    APIService apiService;
    String token;
    TextView productName, productPrice;
    String TAG = "test";


    public static ShopProductList newInstance(String token){

        ShopProductList shopProductList= new ShopProductList();
        Bundle args = new Bundle();
        args.putString("token", token);
        shopProductList.setArguments(args);

        return shopProductList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_company_product_list, container, false);
        apiService= new APIService();
        token = getArguments().getString("token");
        productName = (TextView) view.findViewById(R.id.product_name);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeView);
        mSwipeRefreshLayout.setOnRefreshListener(this);



//        apiService.getProduct(token).enqueue(new Callback<ProductInfo>() {
//            @Override
//            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
//                Log.d(TAG, "get Shop product");
//                productName.setText(response.body().getResults().get(0).getNameOfProduct());
//                productPrice.setText(response.body().getResults().get(0).getPrice() + " Sum");
//            }
//
//            @Override
//            public void onFailure(Call<ProductInfo> call, Throwable t) {
//                Log.d(TAG, "Error" + t.getMessage());
//            }
//        });
//




        return view;



    }


    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);
        Log.d(TAG, "refreshed");
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
//                apiService.getProduct(token).enqueue(new Callback<ProductInfo>() {
//                    @Override
//                    public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
//                        Log.d(TAG, "Success in geting product");
//
//                        productName.setText(response.body().getResults().get(0).getNameOfProduct());
//
//                        productPrice.setText(response.body().getResults().get(0).getPrice() + " Sum");
//                    }
//
//                    @Override
//                    public void onFailure(Call<ProductInfo> call, Throwable t) {
//                        Log.d(TAG, t.getMessage());
//                        Log.d(TAG, "can not do :(");
//                    }
//                });
            }
        });
    }


}
