package com.example.tech6.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tech6.carteasy.Carteasy;
import com.example.tech6.shop.R;
import com.example.tech6.shop.model.Cart;

import java.util.ArrayList;

/**
 * Created by Edwin on 28/02/2015.
 */
public class ViewCartAdapter  extends RecyclerView.Adapter<ViewCartAdapter.ViewHolder> {

    ArrayList<Cart> mItems;
    Context mContext;
    Carteasy cs = new Carteasy();

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

        viewHolder.mProductname.setText(ct.getName());
        viewHolder.mProductdesc.setText(ct.getDescription());
        viewHolder.mProductprice.setText("$" + Integer.toString(ct.getPrice()));
        //viewHolder.mProductthumbnail.setImageResource(ct.getThumbnail());

        String mOtherDetails = "Size: " + ct.getSize() + "  Qty: " + Integer.toString(ct.getQuantity()) + "  Color: " + ct.getColor();

        mOtherDetails = mOtherDetails.substring(0, mOtherDetails.length() - 4) + "...";
        viewHolder.mOtherdetails.setText(mOtherDetails);

        Glide.with(mContext)
                .load(ct.getThumbnail())
                .into(viewHolder.mProductthumbnail);



        //Call up the option button
        viewHolder.optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu menu = new PopupMenu(mContext, v);
                menu.getMenuInflater().inflate(R.menu.menu_cart_option, menu.getMenu());
                Context context = v.getContext();


                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_delete:

                                //This deletes the item on the List
                                String nameOfProduct = mItems.get(i).getName();
                                cs.RemoveId(mItems.get(i).getProductid(), mContext); //remove item from Carteasy
                                mItems.remove(i);  //remove item from RecyclerView
                                notifyDataSetChanged();
                                Toast.makeText(mContext, nameOfProduct + " Removed", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.action_details:
                                //do something
                                Toast.makeText(mContext, "Clicked on view more details", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                menu.show();

            }
        });


    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mProductthumbnail, optionButton;
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
            optionButton = (ImageView) itemView.findViewById(R.id.more_menu_button);


        }
    }
}