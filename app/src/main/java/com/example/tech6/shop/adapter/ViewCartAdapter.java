package com.example.tech6.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.tech6.shop.R;
import com.example.tech6.shop.model.Items;

import java.util.ArrayList;

/**
 * Created by Hp on 27/09/2015.
 */

public class ViewCartAdapter
        extends RecyclerView.Adapter<ViewCartAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Items> mValues;
    public String tabId;
    Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final ImageView mProductthumbnail;
        public final TextView mProductname, mProductdesc, mProductprice, mOtherdetails;
        //public final TextView mTextmoviecategory;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mProductname = (TextView) view.findViewById(R.id.productname);
            mProductdesc = (TextView) view.findViewById(R.id.productdesc);
            mProductprice = (TextView) view.findViewById(R.id.productprice);
            mProductthumbnail =  (ImageView) view.findViewById(R.id.productimage);
            mOtherdetails = (TextView) view.findViewById(R.id.otherdetails);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mProductname.getText();
        }
    }

    public String getValueAt(int position) {
        return mValues.get(position).getProductid();
    }

    public ViewCartAdapter(Context context, ArrayList<Items> items) {
        //context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */

        holder.mProductname.setText(mValues.get(position).getName());
        holder.mProductdesc.setText(mValues.get(position).getDescription());
        holder.mProductprice.setText(mValues.get(position).getPrice());



        Log.d("Found Position: ", Integer.toString(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String[] itemArray = new String[mValues.size()];


            }
        });


            Glide.with(mContext)
                    .load(R.drawable.product1)
                    .into(holder.mProductthumbnail);



    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}