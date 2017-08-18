package inducesmile.com.suumme.activity.company.order;


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

import inducesmile.com.suumme.Interface.MyOnClickListener;
import inducesmile.com.suumme.ObjectClasses.Order;
import inducesmile.com.suumme.ObjectClasses.OrderCompany;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.adapter.OrderAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyOrderList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    APIService apiService;
    String token;
    TextView productName, productPrice;
    String TAG = "test";
    RecyclerView listProductShop;
    OrderAdapter adapter;
    Context context;
    OrderFragmentCompany orderFragmentCompany;

    public static CompanyOrderList newInstance(String token) {

        CompanyOrderList companyOrderList= new CompanyOrderList();
        Bundle args = new Bundle();
        args.putString("token", token);
        companyOrderList.setArguments(args);
        return companyOrderList;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_order_list, container, false);
        apiService = new APIService();
        token = getArguments().getString("token");
        Log.d(TAG, token);
        productName = (TextView) view.findViewById(R.id.product_name);
        productPrice = (TextView) view.findViewById(R.id.product_price);


        listProductShop = (RecyclerView) view.findViewById(R.id.listProductShop);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeView);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listProductShop.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(context, token, mListener);
        listProductShop.setAdapter(adapter);


        apiService.getOrder(token).enqueue(new Callback<OrderCompany>() {
            @Override
            public void onResponse(Call<OrderCompany> call, Response<OrderCompany> response) {
                Log.d(TAG, "Success in geting order");
                listProductShop.setItemAnimator(new SlideInUpAnimator());
                for (Order prod : response.body().getResults()) {
                    adapter.addNewItem(prod);

                }
            }

            @Override
            public void onFailure(Call<OrderCompany> call, Throwable t) {
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
                adapter = new OrderAdapter(context, token, mListener);
                listProductShop.setAdapter(adapter);


                apiService.getOrder(token).enqueue(new Callback<OrderCompany>() {
                    @Override
                    public void onResponse(Call<OrderCompany> call, Response<OrderCompany> response) {
                        Log.d(TAG, "Success in geting order");
                        listProductShop.setItemAnimator(new SlideInUpAnimator());
                        for (Order prod : response.body().getResults()) {
                            adapter.addNewItem(prod);

                        }
                    }

                    @Override
                    public void onFailure(Call<OrderCompany> call, Throwable t) {
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
//            FrameLayout frameLayout = (FrameLayout) getView().findViewById(R.layout.fragment_company_order_list);
            orderFragmentCompany = OrderFragmentCompany.newInstance(token, id, type);
            fragmentManager.beginTransaction().replace(R.id.frameLayout, orderFragmentCompany).addToBackStack( "tag" ).commit();



        }
    };


}
