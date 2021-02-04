package com.example.tech6.carteasy;

import android.content.Context;
import android.content.ContextWrapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tech6 on 10/28/15.
 *
 * printJsonObject function prints a vaue based on the key and id given
 *
 * View function returns a array of values based on id given
 *
 * ViewAll function returns all values saved
 */
public class GetData {

    private Boolean nested = false;
    private Object returnvalue;

    //Print json objects by given id and key
    public Object printJsonObject(String mid, String mkey, JSONObject jobj, Context context) {

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "test.json");
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(mypath));
            JSONObject jsonObj = (JSONObject) obj;
            for (Object key : jsonObj.keySet()) {
                //based on you key types
                String keyStr = (String) key;
                Object keyvalue = jsonObj.get(keyStr);

                //Print key and value
                if (nested == true) {
                    if (keyStr.equals(mkey)) {
                        returnvalue = keyvalue;
                        //System.out.println("Keyvalue: "+keyvalue);
                    }
                }
                //System.out.println("key: "+ keyStr + " value: " + keyvalue);

                //for nested objects iteration if required
                if (keyvalue instanceof JSONObject) {
                    nested = true;
                    printJsonObject(mid, mkey, (JSONObject) keyvalue, context);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnvalue;
    }


    public void view(String id, String key, JSONObject jsonObj, Context context) {

    }


    public void viewAll(Context context) {

    }


}
