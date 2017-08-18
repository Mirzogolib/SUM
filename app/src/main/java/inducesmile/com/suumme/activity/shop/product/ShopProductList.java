package inducesmile.com.suumme.activity.shop.product;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import inducesmile.com.suumme.Interface.MyOnClickListener;
import inducesmile.com.suumme.ObjectClasses.ProductInfo;
import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.activity.company.product.ProductFragmentCompany;
import inducesmile.com.suumme.adapter.DataAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopProductList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ArrayList<ProductInfo> data;
    private DataAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    APIService apiService;
    String token;
    TextView productName, productPrice;
    String TAG = "test";
    RecyclerView listProductShop;
    Context context;
    ProductFragmentCompany productFragmentCompany;

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
        listProductShop = (RecyclerView) view.findViewById(R.id.listProductShop);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listProductShop.setLayoutManager(layoutManager);
        adapter = new DataAdapter(context, token, mListener);
        listProductShop.setAdapter(adapter);
        apiService.getProduct(token).enqueue(new Callback<ProductInfo>() {
            @Override
            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                Log.d(TAG, "Success in geting product");
                listProductShop.setItemAnimator(new SlideInUpAnimator());
                for (ResultsProd prod : response.body().getResults()) {
                        adapter.addNewItem(prod);
                    }

                }



            @Override
            public void onFailure(Call<ProductInfo> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Log.d(TAG, "can not do :(");
            }
        });

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
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                listProductShop.setLayoutManager(layoutManager);
                adapter = new DataAdapter(context, token, mListener);
                listProductShop.setAdapter(adapter);
                apiService.getProduct(token).enqueue(new Callback<ProductInfo>() {
                    @Override
                    public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                        Log.d(TAG, "Success in geting product");
                        listProductShop.setItemAnimator(new SlideInUpAnimator());
                        for (ResultsProd prod : response.body().getResults()) {
                            adapter.addNewItem(prod);
                        }
                    }



                    @Override
                    public void onFailure(Call<ProductInfo> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                        Log.d(TAG, "can not do :(");
                    }
                });
            }
        });
    }



    MyOnClickListener mListener = new MyOnClickListener() {
        @Override
        public void onItemClick(int id) {
            Log.d(TAG, String.valueOf(id));
            int type = 1;
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            productFragmentCompany = ProductFragmentCompany.newInstance(token, id, type);
            fragmentManager.beginTransaction().replace(R.id.frameLayout, productFragmentCompany).addToBackStack( "tag" ).commit();



        }
    };
}
