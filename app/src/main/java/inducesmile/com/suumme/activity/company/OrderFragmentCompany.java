package inducesmile.com.suumme.activity.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import inducesmile.com.suumme.ObjectClasses.Order;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragmentCompany extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    APIService apiService;
    String token;
    int id;
    String TAG = "Test";
    TextView productName, productPrice, shopName, dueDate, totalPrice, productQuantity;
    ImageView productPhoto;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    int type;
    Button status;

    public static OrderFragmentCompany newInstance(String token, int id, int type)  {
        OrderFragmentCompany orderFragmentCompany = new OrderFragmentCompany();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("id", id);
        args.putInt("type", type);
        orderFragmentCompany.setArguments(args);
        return orderFragmentCompany;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_fragment_company, container, false);
        apiService = new APIService();
        token = getArguments().getString("token");
        id= getArguments().getInt("id");
        type = getArguments().getInt("type");
        productName = (TextView) view.findViewById(R.id.productName);
        productPrice=(TextView)view.findViewById(R.id.productPrice);
        shopName= (TextView)view.findViewById(R.id.productShopName);
        productPhoto = (ImageView) view.findViewById(R.id.productPhoto);
        dueDate = (TextView) view.findViewById(R.id.productDate);
        totalPrice = (TextView) view.findViewById(R.id.productTotalPrice);
        productQuantity = (TextView) view.findViewById(R.id.productQuantity);
        status = (Button) view.findViewById(R.id.buttonStatus);
        Log.d(TAG, "type "+ type);
        if(type==0){
            status.setText("Send");

        }else {
            status.setText("Cencel");
        }
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeView2);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i(TAG, "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK ) {
                    Log.i(TAG, "onKey Back listener is working!!!");
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                } else {
                    return false;
                }
            }
        });


        apiService.getOrderById(token, id).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                productName.setText(response.body().getProduct().getName());
                productPhoto.setImageResource(R.drawable.nestle);
                productPrice.setText(response.body().getPrice()+ " sum");
                shopName.setText(response.body().getUser().getUserName());
                dueDate.setText(response.body().getDueDate());
                totalPrice.setText(response.body().getTotalPrice()+ " sum");
                productQuantity.setText(response.body().getQuantity());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public void onRefresh() {

    }
}
