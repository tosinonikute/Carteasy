package com.example.tech6.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tech6.carteasy.Carteasy;
import com.example.tech6.shop.R;
import com.example.tech6.shop.model.Items;

import java.util.ArrayList;


public class DetailActivity extends ActionBarActivity  implements View.OnClickListener{

    private Button addtocart;
    private Toolbar toolbar;
    private int id;

    private ArrayList<Items> mItems;
    private Items myNewItem;

    public ImageView imgThumbnail;
    public TextView name;
    public TextView description;
    public TextView price;

    private ArrayList<String> SizeArray;
    private ArrayList<Integer> QuantityArray;
    private ArrayList<String> ColorArray;

    private String sizeSelected;
    private int quantitySelected;
    private String colorSelected;

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

        myNewItem = mItems.get(id);
        name.setText(myNewItem.getName());
        description.setText(myNewItem.getDescription());
        price.setText("$" + Integer.toString(myNewItem.getPrice()));
        imgThumbnail.setImageResource(myNewItem.getThumbnail());



        /* for fill your Spinner */
        SizeArray = new ArrayList<String>();
        SizeArray.add("SIZES");
        SizeArray.add("small");
        SizeArray.add("medium");
        SizeArray.add("xlarge");

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, SizeArray);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sizespinner = (Spinner) findViewById(R.id.sizespinner);
        sizespinner.setAdapter(sizeAdapter);
        sizespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                sizeSelected = SizeArray.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                sizeSelected = SizeArray.get(1);
            }
        });




        /* for fill your Spinner */
        QuantityArray = new ArrayList<Integer>();
        QuantityArray.add(1);
        QuantityArray.add(2);
        QuantityArray.add(3);

        ArrayAdapter<Integer> qtyAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, QuantityArray);
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner qtyspinner = (Spinner) findViewById(R.id.qtyspinner);
        qtyspinner.setAdapter(qtyAdapter);
        qtyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                quantitySelected = QuantityArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                quantitySelected = QuantityArray.get(0);
            }
        });


        /* for fill your Spinner */
        ColorArray = new ArrayList<String>();
        ColorArray.add("COLOUR");
        ColorArray.add("silver");
        ColorArray.add("black");
        ColorArray.add("brown");

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ColorArray);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner colorspinner = (Spinner) findViewById(R.id.colorspinner);
        colorspinner.setAdapter(colorAdapter);
        colorspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                colorSelected = ColorArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                colorSelected = ColorArray.get(1);
            }
        });



        addtocart = (Button) findViewById(R.id.addtocart);
        addtocart.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.addtocart ){

            //Set size, Quantity and Color
            myNewItem.setSize(sizeSelected);
            myNewItem.setQuantity(quantitySelected);
            myNewItem.setColor(colorSelected);


            //Using carteasy - begin

            Carteasy cs = new Carteasy();
            cs.add(myNewItem.getProductid(), "product id", myNewItem.getProductid());
            cs.add(myNewItem.getProductid(), "product name", myNewItem.getName());
            cs.add(myNewItem.getProductid(), "product desc", myNewItem.getDescription());
            cs.add(myNewItem.getProductid(), "product price", myNewItem.getPrice());
            cs.add(myNewItem.getProductid(), "product thumbnail", myNewItem.getThumbnail());
            cs.add(myNewItem.getProductid(), "product size", myNewItem.getSize());
            cs.add(myNewItem.getProductid(), "product qty", myNewItem.getQuantity());
            cs.add(myNewItem.getProductid(), "product color", myNewItem.getColor());
            cs.commit(getApplicationContext());

            //Using carteasy - end



            Toast.makeText(DetailActivity.this, "Added to cart...", Toast.LENGTH_SHORT).show();
            Context context = v.getContext();
            Intent intent = new Intent(context, ViewCartActivity.class);
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
