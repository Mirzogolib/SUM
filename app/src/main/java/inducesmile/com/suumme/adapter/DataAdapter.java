package inducesmile.com.suumme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.R;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder> {

    List<ResultsProd> productInfos;

    public DataAdapter(List<ResultsProd> _productInfos) {
        productInfos = _productInfos;
    }

    @Override
    public DataAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_sample, parent, false);

        return new DataAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterViewHolder holder, int position) {
        ResultsProd productInfo = productInfos.get(position);
        holder.product_name.setText(productInfo.getNameOfProduct());
        holder.product_price.setText(productInfo.getPrice());

    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }


    public static class DataAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView product_name;
        private TextView product_price;


        DataAdapterViewHolder(View view) {
            super(view);
            product_name = (TextView) view.findViewById(R.id.product_name);
            product_price = (TextView) view.findViewById(R.id.product_price);
//            ButterKnife.bind(this, view);
        }
    }

}
