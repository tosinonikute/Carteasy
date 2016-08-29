package com.example.tech6.carteasy;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by tech6 on 10/28/15.
 */
public class SaveData {

    private Object updateReturnValue;


    /*
       Save function checkes if file exist, recieves data from User
       Appends it and saves into users default app data storage space.
    */
    public void save(Context context, JSONObject items, String mid, JSONObject pro){

        /*
           This retrieves the file from default application data storage if it exist.
           If it doesn't It creates a new file.
        */

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Carteasy.carteasyDirName, Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, Carteasy.carteasyFileName);

        if(mypath.exists()){
            JSONParser parser = new JSONParser();
            try {

                // Get the JSON Object and loop through it.
                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;

                    for (Object key : jsonObj.keySet()) {
                        //based on you key types
                        String keyStr = (String) key;
                        Object keyValue = jsonObj.get(keyStr);

                        if (keyValue instanceof JSONObject) {
                            if(keyStr.toString().equals(mid.toString())){
                                items.remove(keyStr);
                                items.put(keyStr, pro);

                            } else {
                                items.put(keyStr, keyValue);
                            }
                        }
                    }

                    //This writes to file in the user's default app data directory
                    FileWriter filez = new FileWriter(mypath);
                    filez.write(items.toJSONString());
                    filez.flush();
                    filez.close();
                    Log.d("Carteasy: ", "=>data_saved");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {

            try {

                /*
                   This assumes the file does not exist.
                   So It creates a new file.
                */
                FileWriter filez = new FileWriter(mypath);
                filez.write(items.toJSONString());
                filez.flush();
                filez.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /*
        UpdateValue function iterates through the existing data and updates a value based on its key and id
    */
    public void updateValue(String mid, String mkey, Object newvalue, Context context){

        /* Create a new JSON object items to store values */
        JSONObject items = new JSONObject();

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, "test.json");

        if(mypath.exists()){

            JSONParser parser = new JSONParser();
            try {

                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;
                System.out.println("hellokey");

                /* Checks if both the ID and Key exist, if not print an Error message */
                if(checkIfIdExist(mid, jsonObj)) {
                    if (checkIfKeyExist(mid, mkey, jsonObj)) {

                        for (Object key : jsonObj.keySet()) {

                            //based on you key types
                            String keyStr = (String) key;
                            Object keyValue = jsonObj.get(keyStr);


                            //for nested objects iteration if required
                            if(keyValue instanceof JSONObject) {

                                JSONObject products = new JSONObject();

                                /* Loop the JSON object again for nested object */
                                JSONObject newJsonObj = (JSONObject) keyValue;
                                for (Object key2 : newJsonObj.keySet()) {

                                    //based on you key types
                                    String keyStr2 = (String) key2;
                                    Object keyValue2 = newJsonObj.get(keyStr2);

                                    if (keyStr.equals(mid)) {
                                        if (keyStr2.equals(mkey)) {
                                            keyValue2 = newvalue;

                                            /* Notify the user that it has been updated */
                                            Log.d("Carteasy: ", mid + "=>" + mkey + "=>" + newvalue + "=>updated");

                                        }
                                    }
                                    products.put(keyStr2, keyValue2);
                                }
                                items.put(keyStr, products);
                            }

                            //Push to file
                            FileWriter filez = new FileWriter(mypath);
                            filez.write(items.toJSONString());
                            filez.flush();
                            filez.close();
                        }

                    } else {
                        Log.d("Carteasy: ", "Key does not exist");
                    }
                } else {
                    Log.d("Carteasy: ", "ID does not exist");
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }else {

            //Path does not exist
        }

    }



    /*
       checkIfKeyExist function checks if a key exist already, so as not add multiple keys in same ID
       For example: adding productname twice for id => 1234
    */
    public Boolean checkIfKeyExist(String mid, String mkey, JSONObject jsonObj) {

        Boolean exist = false;
        Boolean found = false;

        for (Object key : jsonObj.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            Object keyvalue = jsonObj.get(keyStr);


            if (keyStr.equals(mid)) {
                found = true;
            }

            if (keyvalue instanceof JSONObject) {
                if(found.equals(true)) {
                    JSONObject newJsonObj = (JSONObject) keyvalue;
                    for (Object key2 : newJsonObj.keySet()) {
                        //based on you key types
                        String keyStr2 = (String) key2;
                        Object keyvalue2 = newJsonObj.get(keyStr2);

                        //return value
                        if (keyStr2.equals(mkey)) {
                            exist = true;
                        }

                    }
                    found = false;
                }
            }
        }
        return exist;
    }


    /*
       checkIfIdExist function checks if an ID exist, so as to prevent multiple Ids
     */
    public Boolean checkIfIdExist(String mid, JSONObject jsonObj) {

        Boolean exist = false;

        for (Object key : jsonObj.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            Object keyValue = jsonObj.get(keyStr);


            if (keyStr.equals(mid)) {
                exist = true;
            }
        }
        return exist;
    }





}