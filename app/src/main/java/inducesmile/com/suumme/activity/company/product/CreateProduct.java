package inducesmile.com.suumme.activity.company.product;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inducesmile.com.suumme.R;

public class CreateProduct extends Fragment {


    public CreateProduct() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





         View view = inflater.inflate(R.layout.fragment_create_product, container, false);
        return view;
    }

}
