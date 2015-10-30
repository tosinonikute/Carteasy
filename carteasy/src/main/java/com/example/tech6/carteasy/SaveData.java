package com.example.tech6.carteasy;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

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
    public String save(Context context, JSONObject items, String mid){

        String returnvalue = "data not saved";

        /*
           This retrieves the file from default application data storage if it exist.
           If it doesn't It creates a new file.
        */
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, "test.json");

        if(mypath.exists()){
            System.out.println("Exist");
            JSONParser parser = new JSONParser();
            try {

                // Get the JSON Object and loop through it.
                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;
                if(!checkIfIdExist(mid, jsonObj)) {
                    for (Object key : jsonObj.keySet()) {
                        //based on you key types
                        String keyStr = (String) key;
                        Object keyvalue = jsonObj.get(keyStr);
                        System.out.println("Keyvalue: " + keyvalue);

                        if (keyvalue instanceof JSONObject) {
                            System.out.println("keyStr: " + keyStr);
                            items.put(keyStr, keyvalue);
                        }
                    }

                    /*
                      This writes to file in the user's default app data directory
                    */
                    FileWriter filez = new FileWriter(mypath);
                    filez.write(items.toJSONString());
                    filez.flush();
                    filez.close();

                    returnvalue = "data saved";

                } else {

                    returnvalue = mid+" exist";
                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }else {

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

        return returnvalue;
    }


    /*
        UpdateValue function iterates through the existing data and updates a value based on its key and id
        -> IN PROGRESS
    */
    public Object updateValue(String mid, String mkey, Object newvalue, Context context){
        

        return newvalue;
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
                                    //System.out.println("keyStr: " + keyStr2 + " keyValue: " + keyvalue2);
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
            Object keyvalue = jsonObj.get(keyStr);


            if (keyStr.equals(mid)) {
                exist = true;
            }
        }
        return exist;
    }





}
