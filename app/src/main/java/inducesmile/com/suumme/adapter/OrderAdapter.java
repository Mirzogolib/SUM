package inducesmile.com.suumme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import inducesmile.com.suumme.Interface.MyOnClickListener;
import inducesmile.com.suumme.ObjectClasses.Order;
import inducesmile.com.suumme.R;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderAdapterViewHolder> {
    String TAG = "Test";
    List<Order> productInfos;
    public int id;
    String token;
    Context context;
    MyOnClickListener mListener;

    public OrderAdapter(Context context , String token, MyOnClickListener listener) {
        productInfos = new ArrayList<>();
        this.token = token;
        this.context= context;
        mListener = listener;
    }

    @Override
    public OrderAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_order_sample_list, parent, false);
        return new OrderAdapter.OrderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapterViewHolder holder, int position) {
        final Order productInfo = productInfos.get(position);
        holder.product_name.setText(productInfo.getProduct().getName());
        holder.product_price.setText(productInfo.getTotalPrice());
        holder.product_image.setImageResource(R.drawable.nestle);

        holder.cardViewProductCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(productInfo.getIdOfOrder());
            }
        });


//        holder.cardViewProductCompany.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                id = productInfo.getProduct().getIdOfOrder();
//                Toast.makeText(view.getContext(), String.valueOf(id)+ " item sellected!", Toast.LENGTH_SHORT ).show();
//                Log.d(TAG, String.valueOf(id));
//
//
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }

    public void addNewItem(Order order) {
        productInfos.add(order);
        notifyDataSetChanged();
    }

    static class OrderAdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView product_name;
        private TextView product_price;
        private ImageView product_image;
        private View cardViewProductCompany;


        OrderAdapterViewHolder(View view) {
            super(view);
            product_name = (TextView) view.findViewById(R.id.product_name);
            product_price = (TextView) view.findViewById(R.id.product_price);
            product_image = (ImageView) view.findViewById(R.id.product_photo);
            cardViewProductCompany =  view.findViewById(R.id.cardViewProductCompany);
        }
    }

}
