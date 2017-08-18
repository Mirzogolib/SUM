package inducesmile.com.suumme.activity.company.product;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.adapter.DataAdapter;

public class MakeOrderFragment extends Fragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    APIService apiService;
    String token;
    TextView productName, productPrice;
    String TAG = "test";
    RecyclerView listProductShop;
    DataAdapter adapter;
    Context context;
    int idProduct;

    public static MakeOrderFragment newInstance(String token, int idProduct) {
    MakeOrderFragment makeOrderFragment = new MakeOrderFragment();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("idProduct", idProduct);
        return makeOrderFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        token = getArguments().getString("token");
        idProduct = getArguments().getInt("idProduct");
        View view= inflater.inflate(R.layout.fragment_make_order, container, false);
        return view;
    }













}
