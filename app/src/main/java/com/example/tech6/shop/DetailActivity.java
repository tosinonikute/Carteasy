package com.example.tech6.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DetailActivity extends ActionBarActivity  implements View.OnClickListener{

    private Button addtocart;
    private Toolbar toolbar;
    private int id;
    private ArrayList<Items> mItems;
    public ImageView imgThumbnail;
    public TextView name;
    public TextView description;
    public TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            mItems = (ArrayList<Items>) getIntent().getSerializableExtra("mItems");
        }

        imgThumbnail = (ImageView) findViewById(R.id.img_thumbnail);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        price = (TextView) findViewById(R.id.price);

        Items nature = mItems.get(id);
        name.setText(nature.getName());
        description.setText(nature.getDescription());
        price.setText("$"+Integer.toString(nature.getPrice()));
        imgThumbnail.setImageResource(nature.getThumbnail());



        addtocart = (Button) findViewById(R.id.addtocart);
        addtocart.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.addtocart ){
            Toast.makeText(DetailActivity.this, "Added to cart...", Toast.LENGTH_SHORT).show();
            Context context = v.getContext();
            Intent intent = new Intent(context, ViewCart.class);
            context.startActivity(intent);
        }
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
