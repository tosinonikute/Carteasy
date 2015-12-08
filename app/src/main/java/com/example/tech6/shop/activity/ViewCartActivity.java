package com.example.tech6.shop.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tech6.carteasy.Carteasy;
import com.example.tech6.shop.R;
import com.example.tech6.shop.model.Items;

import java.util.ArrayList;
import java.util.Map;


public class ViewCartActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView view;

    private int id;
    private ArrayList<Items> mItems;

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



        for (Map.Entry<Integer, Map> entry : data.entrySet()) {

            //Retrieve the values of the Map by starting from index 0 - zero

            //System.out.println(entry.getKey() + "=" + entry.getValue());
            System.out.println(entry.getKey() + "=");

            //Get the sub values of the Map
            Map<String, String> innerdata = entry.getValue();
            for (Map.Entry<String, String> entry2 : innerdata.entrySet()) {
                System.out.println(entry2.getKey() + "=" + entry2.getValue());
            }
        }

        //Retrieve the cart details - end


        /*view = (RecyclerView) findViewById(R.id.cartrecyclerview);
        view.setHasFixedSize(true);
        view.setLayoutManager(mLayoutManager);
        view.setAdapter(new ViewCartAdapter(ViewCartActivity.this,
                list));*/


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
