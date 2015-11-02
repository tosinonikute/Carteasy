package com.example.tech6.carteasy;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.parser.ParseException;


/**
 * Created by tech6 on 10/19/15.
 *
 * This Class is the main class which calls functions from other classes when neccessary to perform operations.
 */
public class Carteasy {

    private JSONObject items = new JSONObject();
    private JSONObject products = new JSONObject();
    private String uniqueid;


    //This function Receive values as objects and ties them with an ID
    public void add(String id, String key, Object value){
        uniqueid = id;

        products.put(key, value);
        //Store in items
        items.put(id, products);

    }


    //This function calls the commit function in SaveData Class to save user's input to file.
   public void commit(Context context){
       SaveData sd = new SaveData();
       sd.save(context, items, uniqueid);
   }


    //This function retreives objects value by given id and key and displays it
    public Object get(String id, String key, Context context){
        GetData gd = new GetData();
        return gd.getFile(id, key, context);
    }

    //This function calls the update function in SaveData Class and updates user's input data
    public void update(String mid, String mkey, Object newvalue, Context context){
        SaveData sd = new SaveData();
        sd.updateValue(mid, mkey, newvalue, context);
    }



}

