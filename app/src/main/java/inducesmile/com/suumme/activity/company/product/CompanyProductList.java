package inducesmile.com.suumme.activity.company.product;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import inducesmile.com.suumme.Interface.MyOnClickListener;
import inducesmile.com.suumme.ObjectClasses.ProductInfo;
import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.adapter.DataAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyProductList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    APIService apiService;
    String token;
    TextView productName, productPrice;
    String TAG = "test";
    RecyclerView listProductShop;
    DataAdapter adapter;
    Context context;
    int idProfile;
    ProductFragmentCompany productFragmentCompany;
    CreateProduct createProduct;



    public static CompanyProductList newInstance(String token, int idProfile) {


        CompanyProductList companyProductList = new CompanyProductList();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("idProfile", idProfile);
        companyProductList.setArguments(args);
        return companyProductList;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_product_list, container, false);
        apiService = new APIService();
        token = getArguments().getString("token");
        idProfile = getArguments().getInt("idProfile");
        productName = (TextView) view.findViewById(R.id.product_name);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        listProductShop = (RecyclerView) view.findViewById(R.id.listProductShop);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeView);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "your token is" + token);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                createProduct = CreateProduct.newInstance(token);
                fragmentManager.beginTransaction().replace(R.id.frameLayout, createProduct).addToBackStack( "tag1" ).commit();
            }
        });


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listProductShop.setLayoutManager(layoutManager);
        adapter = new DataAdapter(context, token, mListener);
        listProductShop.setAdapter(adapter);


        apiService.getProductCompany(token, idProfile).enqueue(new Callback<ProductInfo>() {
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


//        apiService.getProduct(token).enqueue(new Callback<ProductInfo>() {
//            @Override
//            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
//                Log.d(TAG, "Success in geting product");
//                listProductShop.setItemAnimator(new SlideInUpAnimator());
//                for (ResultsProd prod : response.body().getResults()) {
//                    int a =  prod.getUserInfoProduct().getIdOfCompany();
//                    if (a == idProfile) {
//                        Log.d(TAG, "company id " + a);
//                        adapter.addNewItem(prod);
//                    }else {
//                        Log.d(TAG, "not entered with id "+a);
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ProductInfo> call, Throwable t) {
//                Log.d(TAG, t.getMessage());
//                Log.d(TAG, "can not do :(");
//            }
//        });
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
                adapter = new DataAdapter(context, token,mListener);
                listProductShop.setAdapter(adapter);
                apiService.getProduct(token).enqueue(new Callback<ProductInfo>() {
                    @Override
                    public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {


                            Log.d(TAG, "Success in geting product");
                            listProductShop.setItemAnimator(new SlideInUpAnimator());
                            for (ResultsProd prod : response.body().getResults()) {
                            int a =  prod.getUserInfoProduct().getIdOfCompany();
                            if (a == idProfile) {
                                Log.d(TAG, "company id " + a);
                                adapter.addNewItem(prod);
                            }else {
                                Log.d(TAG, "not entered with id "+a);
                            }

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
            int type = 0;
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FrameLayout frameLayout = (FrameLayout) getView().findViewById(R.layout.fragment_company_product_list);
            productFragmentCompany = ProductFragmentCompany.newInstance(token, id, type);
            fragmentManager.beginTransaction().replace(R.id.frameLayout, productFragmentCompany).addToBackStack( "tag" ).commit();



        }
    };




}
