package com.example.tech6.shop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tech6.carteasy.Carteasy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText prodid;
    EditText productname;
    EditText proddesc;
    EditText prodq;
    EditText isbn;
    EditText shipping;
    Button addtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prodid = (EditText) findViewById(R.id.productid);
        productname = (EditText) findViewById(R.id.productname);
        proddesc = (EditText) findViewById(R.id.proddesc);
        prodq = (EditText) findViewById(R.id.prodq);
        isbn = (EditText) findViewById(R.id.isbn);
        shipping = (EditText) findViewById(R.id.shipping);
        addtocart = (Button) findViewById(R.id.addtocart);


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(prodid.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Please enter product id", Toast.LENGTH_LONG).show();
                }
                else if(productname.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Please enter product name", Toast.LENGTH_LONG).show();
                }
                else if(proddesc.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Please enter product desc", Toast.LENGTH_LONG).show();
                }
                else if(prodq.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Please enter quantity", Toast.LENGTH_LONG).show();
                }
                else if(isbn.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Please enter isbn", Toast.LENGTH_LONG).show();
                }
                else if(shipping.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Please enter shipping", Toast.LENGTH_LONG).show();
                }else{
                    //work
                    Carteasy n = new Carteasy();
                    n.add(prodid.getText().toString(),"productname", productname.getText().toString());
                    n.add(prodid.getText().toString(),"proddesc", proddesc.getText().toString());
                    n.add(prodid.getText().toString(),"prodq", prodq.getText().toString());
                    n.add(prodid.getText().toString(), "isbn", isbn.getText().toString());
                    n.add(prodid.getText().toString(), "shipping", shipping.getText().toString());
                    n.commit(getApplicationContext());


                    prodid.setText("");
                    productname.setText("");
                    proddesc.setText("");
                    prodq.setText("");
                    isbn.setText("");
                    shipping.setText("");


                }

            }

        });



        //Carteasy n = new Carteasy();
        //x11 is product id
        /*n.add("x11", "name", "Barza");
        n.add("x11", "quantity", "description of the product");
        n.add("x11", "price", 97.51);
        n.add("x11", "quantity", 7);
        n.add("x11", "shipping", false);
        n.add("x11", "isbn", 937883833L);

        //x12 is product id
        n.add("x12", "name", "Jumbo");
        n.add("x12", "quantity", "description of the product");
        n.add("x12", "price", 32.50);
        n.add("x12", "quantity", 10);
        n.add("x12", "shipping", true);
        n.add("x12", "isbn", 2902882981L);

        n.viewAll();*/

        //Long isbn = (Long) n.get("x12", "isbn");

        //Log.d("ISBN: ", isbn.toString());



        /*JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        JSONArray list = new JSONArray();
        JSONObject obj2 = new JSONObject();
        JSONObject obj3 = new JSONObject();
        obj3.put("msg 1","1");
        obj3.put("msg 2","2");
        obj3.put("msg 3","3");

        JSONObject obj4 = new JSONObject();
        obj4.put("msg 1","4");
        obj4.put("msg 2", "5");
        obj4.put("msg 3","6");


        obj2.put("obj3",obj3);
        obj2.put("obj4",obj4);



        list.add(obj2);

        obj.put("messages", list);

        System.out.println("keyman: "+ obj);*/


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
