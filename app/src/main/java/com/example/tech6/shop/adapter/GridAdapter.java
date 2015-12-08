package com.example.tech6.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tech6.shop.activity.DetailActivity;
import com.example.tech6.shop.model.Items;
import com.example.tech6.shop.R;

import java.util.ArrayList;

/**
 * Created by Edwin on 28/02/2015.
 */
public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    ArrayList<Items> mItems;

    public GridAdapter() {
        super();
        mItems = new ArrayList<Items>();
        Items species = new Items();
        species.setProductid("E101");
        species.setName("Fontana Milano");
        species.setDescription("Tum Lady Tote");
        species.setPrice(1020);
        species.setThumbnail(R.drawable.product1);
        mItems.add(species);

        species = new Items();
        species.setProductid("E102");
        species.setName("Fendi");
        species.setDescription("Peekaboo Satchel");
        species.setPrice(8720);
        species.setThumbnail(R.drawable.product2);
        mItems.add(species);

        species = new Items();
        species.setProductid("E103");
        species.setName("Stella Mccartney");
        species.setDescription("Becket Shoulder Bag");
        species.setPrice(2120);
        species.setThumbnail(R.drawable.product3);
        mItems.add(species);

        species = new Items();
        species.setProductid("E104");
        species.setName("Truss");
        species.setDescription("Woven Medium Tote");
        species.setPrice(9720);
        species.setThumbnail(R.drawable.product4);
        mItems.add(species);

        species = new Items();
        species.setProductid("E105");
        species.setName("FENDI");
        species.setDescription("Peekaboo Satchel");
        species.setPrice(8900);
        species.setThumbnail(R.drawable.product5);
        mItems.add(species);

        species = new Items();
        species.setProductid("E106");
        species.setName("Givenchy");
        species.setDescription("Pandora Mini-Bag");
        species.setPrice(4520);
        species.setThumbnail(R.drawable.product6);
        mItems.add(species);

        species = new Items();
        species.setProductid("E107");
        species.setName("Givenchy");
        species.setDescription("Antigona Duffel Bag");
        species.setPrice(6720);
        species.setThumbnail(R.drawable.product7);
        mItems.add(species);

        species = new Items();
        species.setProductid("E108");
        species.setName("FENDI");
        species.setDescription("Peekaboo Shearling");
        species.setPrice(1320);
        species.setThumbnail(R.drawable.product8);
        mItems.add(species);

        species = new Items();
        species.setProductid("E109");
        species.setName("LANVIN");
        species.setDescription("Lanvin Clutch");
        species.setPrice(7720);
        species.setThumbnail(R.drawable.product9);
        mItems.add(species);

        species = new Items();
        species.setProductid("E110");
        species.setName("BALENCIAGA");
        species.setDescription("Papier A6 Side-Zip");
        species.setPrice(720);
        species.setThumbnail(R.drawable.product10);
        mItems.add(species);

        species = new Items();
        species.setProductid("E111");
        species.setName("GIVENCHY");
        species.setDescription("Pandora Medium");
        species.setPrice(2720);
        species.setThumbnail(R.drawable.product11);
        mItems.add(species);

        species = new Items();
        species.setProductid("E112");
        species.setName("OPENING CEREMONY");
        species.setDescription("Misha Long Wallet");
        species.setPrice(1720);
        species.setThumbnail(R.drawable.product12);
        mItems.add(species);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Items nature = mItems.get(i);
        viewHolder.name.setText(nature.getName());
        viewHolder.description.setText(nature.getDescription());
        viewHolder.price.setText("$"+Integer.toString(nature.getPrice()));
        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());

        Glide.with(viewHolder.imgThumbnail.getContext())
                .load(nature.getThumbnail())
                .into(viewHolder.imgThumbnail);

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", i);
                intent.putExtra("mItems", mItems);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView name;
        public TextView description;
        public TextView price;
        public final View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            name = (TextView)itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            price = (TextView)itemView.findViewById(R.id.price);
        }
    }
}