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
 *  Created by tech6 on 10/28/15.
 *
 *  This class takes care of retreiving data from JSON file and looping through it to return values to the user.
 *
 */
public class GetData {

    private Boolean nested = false;
    private Object returnvalue;
    private Object printJsonvalue;


    /*
       getFile function looks for the file and passes it to the printJsonObject function which loops through it and
       displays the content of the file.
     */
    public Object getFile(String mid, String mkey, Context context) {

        returnvalue = "";
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, "test.json");
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(mypath));
            JSONObject jsonObj = (JSONObject) obj;
            //System.out.println("Keyvalue: "+jsonObj);
            returnvalue = printJsonObject(mid, mkey, jsonObj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnvalue;
    }


    /*
       printJsonObject function recieves file from getFile function and picks the file as an object and loop
       through it based on the key and values given.
     */
    public Object printJsonObject(String mid, String mkey, JSONObject jsonObj) {

        printJsonvalue = "";
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            Object keyvalue = jsonObj.get(keyStr);

            //System.out.println("key: "+ keyStr + " value: " + keyvalue);

            //Loops for the second time for nested objects iteration if required
            if (keyvalue instanceof JSONObject) {
                //printJsonObject(mid, mkey, (JSONObject) keyvalue);

                JSONObject newJsonObj = (JSONObject) keyvalue;
                for (Object key2 : newJsonObj.keySet()) {
                    //based on you key types
                    String keyStr2 = (String) key2;
                    Object keyvalue2 = newJsonObj.get(keyStr2);

                        //return value
                    if (keyStr.equals(mid)) {
                        if (keyStr2.equals(mkey)) {
                            printJsonvalue = keyvalue2;
                            //System.out.println("Keyvalue2: "+keyvalue2);
                        }
                    }

                }
            }
        }
       return printJsonvalue;
    }


    /*
       View function returns a array of values based on id given
       This works best if the user specify for example:  user specifies id as 1243, and productname as key,
       It returns the respective Value of the key.
    */
    public void view(String id, String key, JSONObject jsonObj, Context context) {

    }



    /*
      ViewAll returns all values the values saved for example:
      id , productname, product_desc, isbn_no, qunatity e.t.c
    */
    public void viewAll(Context context) {

    }


}
