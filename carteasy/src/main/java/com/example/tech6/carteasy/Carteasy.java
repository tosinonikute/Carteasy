package com.example.tech6.carteasy;

import android.util.Log;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Created by tech6 on 10/19/15.
 */
public class Carteasy {

    JSONObject items = new JSONObject();
    JSONObject product = new JSONObject();
    Boolean nested = false;
    Object returnvalue;

    //Receive values as objects and typecast to appropriate type
    public void add(String id, String key, Object value){

        //if Value is Double
        if(value.getClass()==Double.class){
            Double newvalue = (Double)value;
            product.put(key, new Double(newvalue));
        }
        //if Value is Float
        if(value.getClass()==Float.class){
            Float newvalue = (Float)value;
            product.put(key, new Float(newvalue));
        }
        //if Value is String
        if(value.getClass()==String.class){
            String newvalue = (String)value;
            product.put(key, new String(newvalue));
        }
        //if Value is Integer
        if(value.getClass()==Integer.class){
            Integer newvalue = (Integer)value;
            product.put(key, new Integer(newvalue));
        }
        //if Value is Character
        if(value.getClass()==Character.class){
            Character newvalue = (Character)value;
            product.put(key, new Character(newvalue));
        }
        //if Value is Long
        if(value.getClass()==Long.class){
            Long newvalue = (Long)value;
            product.put(key, new Long(newvalue));
        }
        //if Value is Short
        if(value.getClass()==Short.class){
            Short newvalue = (Short)value;
            product.put(key, new Short(newvalue));
        }

        //Store in items
        items.put(id, product);

    }


    //Get objects value by given id and key
    public Object get(String id, String key){
        return printJsonObject(id, key, items);
    }


    //Print json objects by given id and key
    public Object printJsonObject(String mid, String mkey, JSONObject jsonObj) {

        for (Object key : jsonObj.keySet()) {
            //based on you key types
            String keyStr = (String)key;
            Object keyvalue = jsonObj.get(keyStr);

            //Print key and value
            if(nested==true){
                if(keyStr.equals(mkey)){
                    returnvalue = keyvalue;
                    //System.out.println("Keyvalue: "+keyvalue);
                }
            }
            //System.out.println("key: "+ keyStr + " value: " + keyvalue);

            //for nested objects iteration if required
            if (keyvalue instanceof JSONObject) {
                nested = true;
                printJsonObject(mid, mkey, (JSONObject) keyvalue);
            }
        }

        return returnvalue;
    }

    //Check if an item exist
    public void checkIfExist(){

    }

    //Remove any item from the stored object
    public void remove(String id, String key){

    }

    //Remove all items
    public void removeAll(){

    }

    //Modify a particular item and change it values
    public void modify(String id, String key, Object newvalue){

    }

    //List all items saved and return them all as an array
    public void viewAll(){

    }

    //List item saved with an id and return it as an array
    public void view(String id, String key){

    }







}

