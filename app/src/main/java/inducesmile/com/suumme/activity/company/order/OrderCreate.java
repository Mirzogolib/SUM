package inducesmile.com.suumme.activity.company.order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inducesmile.com.suumme.R;


public class OrderCreate extends Fragment {


    public static OrderCreate newInstane(String token) {
        OrderCreate orderCreate = new OrderCreate();
        Bundle args = new Bundle();
        args.putString("token", token);
        orderCreate.setArguments(args);
        return orderCreate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_create, container, false);






        return view;
    }

}
