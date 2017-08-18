package inducesmile.com.suumme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import inducesmile.com.suumme.Interface.MyOnClickListener;
import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.R;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>{
    String TAG = "Test";
    List<ResultsProd> productInfos;
    public int id;
    String token;
    Context context;
    MyOnClickListener mListener;


    public DataAdapter(Context context , String token, MyOnClickListener listener) {
        productInfos = new ArrayList<>();
        this.token = token;
        this.context= context;
        mListener = listener;


    }

    @Override
    public DataAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_order_sample_list, parent, false);

        return new DataAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapterViewHolder holder, final int position) {
        final ResultsProd productInfo = productInfos.get(position);

            holder.product_name.setText(productInfo.getNameOfProduct());
            holder.product_price.setText(productInfo.getPrice());
//        String image = productInfo.getImage()+ "ok";
            holder.product_image.setImageResource(R.drawable.product1);
//        if (holder.product_image==null) {
//            holder.product_image.setImageResource(R.drawable.sum);
//        } else {
//            Glide.with(context).load(productInfo.getImage()).into(holder.product_image);
//        }
            holder.cardViewProductCompany.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(productInfo.getIdOfProduct());
                    Log.d(TAG, productInfo.getImage() + " ");

                }
            });

    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }

    public void addNewItem(ResultsProd item) {
        productInfos.add(item);
        notifyDataSetChanged();
    }

    public void clearList(){
        productInfos.clear();
        notifyDataSetChanged();
    }


    static class DataAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView product_name;
        private TextView product_price;
        private ImageView product_image;
        private View cardViewProductCompany;


        DataAdapterViewHolder(View view) {
            super(view);
            product_name = (TextView) view.findViewById(R.id.product_name);
            product_price = (TextView) view.findViewById(R.id.product_price);
            product_image = (ImageView) view.findViewById(R.id.product_photo);
            cardViewProductCompany =  view.findViewById(R.id.cardViewProductCompany);
        }
    }


}
