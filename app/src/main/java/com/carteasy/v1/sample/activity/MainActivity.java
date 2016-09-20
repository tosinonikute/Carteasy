package com.carteasy.v1.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carteasy.v1.sample.model.Cart;
import com.carteasy.v1.lib.Carteasy;
import com.carteasy.v1.sample.R;
import com.carteasy.v1.sample.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Toolbar toolbar;
    private ArrayList<Cart> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        // Calling the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter();
        mRecyclerView.setAdapter(mAdapter);

        //Retrieve the cart details - begin
        Map<Integer, Map> data;
        Carteasy cs = new Carteasy();
        data = cs.ViewAll(getApplicationContext());
        mItems = new ArrayList<Cart>();
        Cart cartitem = new Cart();

        //check if retrieved any size greater than 0 & not null
        if(data != null && data.size() > 0) {
            for (Map.Entry<Integer, Map> entry : data.entrySet()) {
                mItems.add(cartitem);
            }
        }

        //Set MyBag ( NoOfItems );
        TextView NoOfItems = (TextView) findViewById(R.id.no_of_items);
        NoOfItems.setText("MY BAG ("+Integer.toString(mItems.size())+")");

        //Navigate to Cart Activity
        LinearLayout myBag = (LinearLayout) findViewById(R.id.my_bag);
        myBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ViewCartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // call this to finish the current activity
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
