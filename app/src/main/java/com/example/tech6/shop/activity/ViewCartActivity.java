package com.example.tech6.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tech6.carteasy.Carteasy;
import com.example.tech6.shop.R;
import com.example.tech6.shop.adapter.ViewCartAdapter;
import com.example.tech6.shop.model.Cart;

import java.util.ArrayList;
import java.util.Map;


public class ViewCartActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private TextView NoOfItems;
    private Button continuebutton;
    private ImageView optionButton;

    private int id;

    ArrayList<Cart> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Retrieve the cart details - begin

        Map<Integer, Map> data;
        Carteasy cs = new Carteasy();
        data = cs.ViewAll(getApplicationContext());



        mItems = new ArrayList<Cart>();
        Cart cartitem = new Cart();
        for (Map.Entry<Integer, Map> entry : data.entrySet()) {

            //Retrieve the values of the Map by starting from index 0 - zero

            cartitem = new Cart();
            //Get the sub values of the Map
            Map<String, String> innerdata = entry.getValue();
            for (Map.Entry<String, String> entry2 : innerdata.entrySet()) {
                System.out.println(entry2.getKey() + "=" + entry2.getValue());

                String product = entry2.getKey();
                switch (product) {
                    case "product id":  cartitem.setProductid(entry2.getValue());
                        break;
                    case "product name":  cartitem.setName(entry2.getValue());
                        break;
                    case "product desc":  cartitem.setDescription(entry2.getValue());
                        break;
                    case "product qty":  cartitem.setQuantity(Integer.parseInt(entry2.getValue()));
                        break;
                    case "product size":  cartitem.setSize(entry2.getValue());
                        break;
                    case "product price":  cartitem.setPrice(Integer.parseInt(entry2.getValue()));
                        break;
                    case "product color":  cartitem.setColor(entry2.getValue());
                        break;
                    case "product thumbnail":  cartitem.setThumbnail(Integer.parseInt(entry2.getValue()));
                        break;
                }
            }
            mItems.add(cartitem);
        }

        //Set MyBag ( NoOfItems );
        NoOfItems = (TextView) findViewById(R.id.no_of_items);
        NoOfItems.setText("MY BAG ("+Integer.toString(mItems.size())+")");


        System.out.println(mItems.get(0).getColor());
        //Retrieve the cart details - end


        // Calling the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new ViewCartAdapter(ViewCartActivity.this, mItems);
        mRecyclerView.setAdapter(mAdapter);


        //Navigate the User back to the display activity
        continuebutton = (Button) findViewById(R.id.continueshopping);
        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // call this to finish the current activity
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
