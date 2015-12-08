package com.example.tech6.carteasy;

import android.content.Context;

import org.json.simple.JSONObject;

import java.util.Map;


/**
 * Created by tech6 on 10/19/15.
 *
 * This Class is the main class which calls functions from other classes when neccessary to perform operations.
 */
public class Carteasy {

    private JSONObject items = new JSONObject();
    private JSONObject products = new JSONObject();
    private String uniqueId;
    public static int applaunched = 0;


    /* check when app is first run, to clear the JSON file */






    //This function Receive values as objects and ties them with an ID
    public void add(String id, String key, Object value){
        uniqueId = id;

        products.put(key, value);
        //Store in items
        items.put(id, products);

    }


    //This function calls the commit function in SaveData Class to save user's input to file.
   public void commit(Context context){
       clearPreviousData(context);
       SaveData sd = new SaveData();
       sd.save(context, items, uniqueId);
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

    //This function removes any key specified by the user
    public void Removekey(String mid, String mkey, Context context){
        RemoveData rm = new RemoveData();
        rm.RemoveDataByKey(mid, mkey, context);
    }


    //This function removes data based on any key specified by the user
    public void RemoveId(String mid, Context context){
        RemoveData rm = new RemoveData();
        rm.RemoveDataById(mid, context);
    }

    public Map ViewData(String mid, Context context){
        GetData gd = new GetData();
        //Object itemObj = rm.ViewById(mid, context);
        //HashMap<String, String> newitems = new HashMap<String, String>();
        return gd.ViewById(mid, context);
    }

    public Map ViewAll(Context context){
        GetData gd = new GetData();
        //Object itemObj = rm.ViewById(mid, context);
        //HashMap<String, String> newitems = new HashMap<String, String>();
        return gd.ViewAll(context);
    }


    /** This functions below will retrieve data and typecast it before returning it the user **/

    public String getString(String id, String key, Context context){
        String value = "";
        value = (String) get(id, key, context);
        return value;
    }

    public Integer getInteger(String id, String key, Context context){
        Integer value = 0;
        value = (Integer) get(id, key, context);
        return value;
    }

    public Float getFloat(String id, String key, Context context){
        float value = 0;
        value = (Float) get(id, key, context);
        return value;
    }

    public Double getDouble(String id, String key, Context context){
        Double value = 0.2;
        value = (Double) get(id, key, context);
        return value;
    }

    public Long getLong(String id, String key, Context context){
        Long value = 9484480494l;
        value = (Long) get(id, key, context);
        return value;
    }

    public Short getShort(String id, String key, Context context){
        Short value = 9446;
        value = (Short) get(id, key, context);
        return value;
    }

    public void clearPreviousData(Context context){
        if(applaunched == 0){
            RemoveData rm = new RemoveData();
            rm.ClearAllData(context);
            applaunched = 1;
            System.out.println("IN APPLAUNCHED");
        }
    }


}

