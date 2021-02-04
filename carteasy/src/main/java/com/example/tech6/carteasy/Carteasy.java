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
 * Add function adds each value to items JSONobject
 *
 * Commit function calls the save function in SaveData class
 *
 * get function calls the GetData class to retrieve values based on key and value given
 */
public class Carteasy {

    private JSONObject items = new JSONObject();
    private JSONObject products = new JSONObject();
    private String uniqueid;


    //Receive values as objects and typecast to appropriate type
    public void add(String id, String key, Object value){
        uniqueid = id;

        //if Value is Double
        if(value.getClass()==Double.class){
            Double newvalue = (Double)value;
            products.put(key, new Double(newvalue));
        }
        //if Value is Float
        if(value.getClass()==Float.class){
            Float newvalue = (Float)value;
            products.put(key, new Float(newvalue));
        }
        //if Value is String
        if(value.getClass()==String.class){
            String newvalue = (String)value;
            products.put(key, new String(newvalue));
        }
        //if Value is Integer
        if(value.getClass()==Integer.class){
            Integer newvalue = (Integer)value;
            products.put(key, new Integer(newvalue));
        }
        //if Value is Character
        if(value.getClass()==Character.class){
            Character newvalue = (Character)value;
            products.put(key, new Character(newvalue));
        }
        //if Value is Long
        if(value.getClass()==Long.class){
            Long newvalue = (Long)value;
            products.put(key, new Long(newvalue));
        }
        //if Value is Short
        if(value.getClass()==Short.class){
            Short newvalue = (Short)value;
            products.put(key, new Short(newvalue));
        }


        //Store in items
        items.put(id, products);

        System.out.println("itemvalue: " + items);
    }


   //save data
   public String commit(Context context){
       SaveData sd = new SaveData();
       return sd.save(context, items, uniqueid);
   }



    //Get objects value by given id and key
    public Object get(String id, String key, Context context){
        GetData gd = new GetData();
        return gd.printJsonObject(id, key, items, context);
    }











}

