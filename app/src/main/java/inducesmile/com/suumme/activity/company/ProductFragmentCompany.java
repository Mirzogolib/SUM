package inducesmile.com.suumme.activity.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFragmentCompany extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
APIService apiService;
    String token;
    int id;
    String TAG = "Test";
    TextView productName, productPrice, companyName, definition;
    ImageView productPhoto;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static ProductFragmentCompany newInstance(String token, int id) {


        ProductFragmentCompany productFragmentCompany = new ProductFragmentCompany();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("id", id);
        productFragmentCompany.setArguments(args);
        return productFragmentCompany;



    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_fragment_company, container, false);
        apiService = new APIService();
        token = getArguments().getString("token");
        id= getArguments().getInt("id");
        productName = (TextView) view.findViewById(R.id.productName);
        productPrice=(TextView)view.findViewById(R.id.productPrice);
        companyName =(TextView)view.findViewById(R.id.productCompanyName);
        definition = (TextView)view.findViewById(R.id.definition);
        productPhoto = (ImageView) view.findViewById(R.id.productPhoto);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeView2);
        mSwipeRefreshLayout.setOnRefreshListener(this);




        apiService.getProductById(token, id).enqueue(new Callback<ResultsProd>() {
            @Override
            public void onResponse(Call<ResultsProd> call, Response<ResultsProd> response) {
                Log.d(TAG, response.body().getNameOfProduct());
                productName.setText(response.body().getNameOfProduct());
                productPrice.setText(response.body().getPrice()+ " sum");
                companyName.setText(response.body().getUserInfoProduct().getCompanyName());
                definition.setText(response.body().getDefinition());
                productPhoto.setImageResource(R.drawable.nestle);
            }

            @Override
            public void onFailure(Call<ResultsProd> call, Throwable t) {

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
                apiService.getProductById(token, id).enqueue(new Callback<ResultsProd>() {
                    @Override
                    public void onResponse(Call<ResultsProd> call, Response<ResultsProd> response) {
                        Log.d(TAG, response.body().getNameOfProduct());
                        productName.setText(response.body().getNameOfProduct());
                        productPrice.setText(response.body().getPrice()+ " sum");
                        companyName.setText(response.body().getUserInfoProduct().getCompanyName());
                        definition.setText(response.body().getDefinition());
                        productPhoto.setImageResource(R.drawable.nestle);
                    }

                    @Override
                    public void onFailure(Call<ResultsProd> call, Throwable t) {

                    }
                });
            }
        });
    }
}
