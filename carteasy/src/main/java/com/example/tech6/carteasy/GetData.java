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
import java.util.HashMap;
import java.util.Map;

/**
 *  Created by tech6 on 10/28/15.
 *
 *  This class takes care of retreiving data from JSON file and looping through it to return values to the user.
 *
 */
public class GetData {

    private Boolean nested = false;
    private Object returnValue;
    private Object printJsonValue;


    /*
       getFile function looks for the file and passes it to the printJsonObject function which loops through it and
       displays the content of the file.
     */
    public Object getFile(String mid, String mkey, Context context) {

        returnValue = "";
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, "test.json");
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(mypath));
            JSONObject jsonObj = (JSONObject) obj;
            //System.out.println("Keyvalue: "+jsonObj);
            returnValue = printJsonObject(mid, mkey, jsonObj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnValue;
    }


    /*
       printJsonObject function recieves file from getFile function and picks the file as an object and loop
       through it based on the key and values given.
     */
    public Object printJsonObject(String mid, String mkey, JSONObject jsonObj) {

        printJsonValue = "";
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
                            printJsonValue = keyvalue2;
                            //System.out.println("Keyvalue2: "+keyvalue2);
                        }
                    }

                }
            }
        }
       return printJsonValue;
    }


    /*
       View function returns a array of values based on id given
       This works best if the user specify for example:  user specifies id as 1243, and productname as key,
       It returns the respective Value of the key.
    */
    public Map ViewById(String mid, Context context){

        Map<String, String> newItems = new HashMap<String, String>();

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
                SaveData sd = new SaveData();

                /* Checks if both the ID exist, if not print an Error message */
                if(sd.checkIfIdExist(mid, jsonObj)) {

                    for (Object key : jsonObj.keySet()) {

                        //based on you key types
                        String keyStr = (String) key;
                        Object keyValue = jsonObj.get(keyStr);


                        //for nested objects iteration if required
                        if(keyValue instanceof JSONObject) {

                            if(keyStr.equals(mid)) {

                                /* Loop the JSON object again for nested object */
                                JSONObject newJsonObj = (JSONObject) keyValue;
                                for (Object key2 : newJsonObj.keySet()) {

                                    //based on you key types
                                    String keyStr2 = (String) key2;
                                    Object keyvalue2 = newJsonObj.get(keyStr2);

                                    //Cast the value to String
                                    String strValue = String.valueOf(keyvalue2);

                                    newItems.put(keyStr2, strValue);

                                    System.out.println("keyValue: " + keyValue);

                                }
                                return newItems;
                            }
                        }

                    }

                } else {
                    System.out.println("Key does not exist");
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {

            //Path does not exist
        }

        return null;
    }



    /*
      ViewAll returns all values the values saved for example:
      id , productname, product_desc, isbn_no, qunatity e.t.c
    */
    public Map ViewAll(Context context){


        Map<Integer, Map> mainItems = new HashMap<Integer, Map>();
        int count = 0;

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
                SaveData sd = new SaveData();

                    for (Object key : jsonObj.keySet()) {

                        //based on you key types
                        String keyStr = (String) key;
                        Object keyvalue = jsonObj.get(keyStr);

                        //for nested objects iteration if required
                        if(keyvalue instanceof JSONObject) {

                            Map<String, String> newItems = new HashMap<String, String>();

                                /* Loop the JSON object again for nested object */
                                JSONObject newJsonObj = (JSONObject) keyvalue;
                                for (Object key2 : newJsonObj.keySet()) {

                                    //based on you key types
                                    String keyStr2 = (String) key2;
                                    Object keyValue2 = newJsonObj.get(keyStr2);

                                    //Cast the value to String
                                    String strValue = String.valueOf(keyValue2);

                                    newItems.put(keyStr2, strValue);

                                    //System.out.println("keyvalue: " + keyValue);

                                }

                            mainItems.put(count, newItems);
                            count++;
                        }

                    }
                    return mainItems;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {

            //Path does not exist
        }

        return null;
    }




}
