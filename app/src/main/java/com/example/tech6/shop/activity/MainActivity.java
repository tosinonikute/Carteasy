package com.example.tech6.shop.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tech6.shop.R;
import com.example.tech6.shop.adapter.GridAdapter;

public class MainActivity extends ActionBarActivity  implements android.view.View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Set a Toolbar to replace the ActionBar.
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



        //Carteasy cs = new Carteasy();
        //cs.add("6701", "product name", "cigarate box");
        //cs.add("6701", "product price", 200.00);
        //cs.commit(getApplicationContext());

        //Button button1 = (Button) findViewById(R.id.button1);
        //button1.setOnClickListener(this);

        //fetch data
        //System.out.println("CS: " + cs.getString("6701", "product name", getApplicationContext()) );
        //System.out.println("CS: " + cs.Viewdata("6701", getApplicationContext()));


    }

    public void onClick(View v) {
        /*if (v.getId() == R.id.button1 ) {

            Carteasy cs = new Carteasy();
            cs.add("6702", "product name", "fruit bucket");
            cs.add("6702", "product price", 300.00);
            cs.commit(getApplicationContext());

            //System.out.println("CSS: " + cs.ViewData("6701", getApplicationContext()) );

            Map<String, String> data;
            data = cs.Viewdata("6701", getApplicationContext());
            System.out.println("CSS: " + data );

            for (Map.Entry<String, String> entry : data.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }


        }*/
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
