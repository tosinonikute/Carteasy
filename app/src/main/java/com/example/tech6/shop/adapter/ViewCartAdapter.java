package com.example.tech6.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tech6.shop.R;
import com.example.tech6.shop.model.Cart;

import java.util.ArrayList;

/**
 * Created by Edwin on 28/02/2015.
 */
public class ViewCartAdapter  extends RecyclerView.Adapter<ViewCartAdapter.ViewHolder> {

    ArrayList<Cart> mItems;
    Context mContext;

    public ViewCartAdapter(Context context, ArrayList<Cart> item ) {
        mContext = context;
        mItems = item;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        Cart ct = mItems.get(i);
        System.out.println("Found position:" + i);

        viewHolder.mProductname.setText(ct.getName());
        viewHolder.mProductdesc.setText(ct.getDescription());
        viewHolder.mProductprice.setText("$" + Integer.toString(ct.getPrice()));
        viewHolder.mProductthumbnail.setImageResource(ct.getThumbnail());
        viewHolder.mOtherdetails.setText("Size: " + ct.getSize() + "  Qty: " + Integer.toString(ct.getQuantity()) + "  Color: " + ct.getColor());

        Glide.with(mContext)
                .load(ct.getThumbnail())
                .into(viewHolder.mProductthumbnail);


    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mProductthumbnail;
        public final TextView mProductname, mProductdesc, mProductprice, mOtherdetails;
        public final View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mProductname = (TextView) itemView.findViewById(R.id.productname);
            mProductdesc = (TextView) itemView.findViewById(R.id.productdesc);
            mProductprice = (TextView) itemView.findViewById(R.id.productprice);
            mProductthumbnail =  (ImageView) itemView.findViewById(R.id.productimage);
            mOtherdetails = (TextView) itemView.findViewById(R.id.otherdetails);


        }
    }
}