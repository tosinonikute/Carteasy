package com.carteasy.v1.lib;

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
import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *  Created by Tosin Onikute on 10/28/15.
 *
 *  This class takes care of retreiving data from JSON file and looping through it to return values to the user.
 *
 */
public class GetData {

    private Object returnValue;
    private Object printJsonValue;

    /* getFile function looks for the file and passes it to the printJsonObject function which loops through it and
       displays the content of the file. */
    public Object getFile(String mid, String mkey, Context context) {

        returnValue = "";
        // Create imageDir in applications default directory
        File mypath = new File(Carteasy.getContextWrapper(context), Carteasy.carteasyFileName);
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(mypath));
            JSONObject jsonObj = (JSONObject) obj;
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

    /* printJsonObject function recieves file from getFile function and picks the file as an object and loop
       through it based on the key and values given. */
    public Object printJsonObject(String mid, String mkey, JSONObject jsonObj) {

        printJsonValue = "";
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            Object keyvalue = jsonObj.get(keyStr);

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
    public Map<String, String> ViewById(String mid, Context context){

        //http://stackoverflow.com/questions/22606572/android-json-parsing-of-multiple-jsonobjects-inside-jsonobject?rq=1

        Map<String, String> newItems = new HashMap<String, String>();

        /* Create a new JSON object items to store values */
        JSONObject items = new JSONObject();

        // Create imageDir in applications default directory
        File mypath = new File(Carteasy.getContextWrapper(context), Carteasy.carteasyFileName);

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
                                }
                                return newItems;
                            }
                        }
                    }

                } else {
                    Log.d("Carteasy: ","Key does not exist");
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
        return newItems;
    }

    /* ViewAll returns all values the values saved for example:
      id , productname, product_desc, isbn_no, qunatity e.t.c */
    public Map<Integer, Map> ViewAll(Context context){

        Map<Integer, Map> mainItems = new HashMap<Integer, Map>();
        int count = 0;

        /* Create a new JSON object items to store values */
        JSONObject items = new JSONObject();

        // Create imageDir in applications default directory
        File mypath = new File(Carteasy.getContextWrapper(context), Carteasy.carteasyFileName);

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
        return mainItems;
    }

    public Boolean getPersistStatus(Context context){
        Boolean status = false;
        File mypath = new File(Carteasy.getContextWrapper(context), Carteasy.settingsFileName);
        JSONParser parser = new JSONParser();
        if(mypath.exists()) {
            try {
                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;
                for (Object key : jsonObj.keySet()) {
                    String keyStr = (String) key;
                    if (keyStr.equals(Carteasy.persist)) {
                        Object keyValue = jsonObj.get(keyStr);
                        if (keyValue.equals(true)) {
                            status = true;
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public Boolean doesIdExist(String mid, Context context){

        // Create fileDir in applications default directory
        final File myPath = new File(Carteasy.getContextWrapper(context), Carteasy.carteasyFileName);

        if(myPath.exists()){
            JSONParser parser = new JSONParser();
            try {

                final Object obj = parser.parse(new FileReader(myPath));
                final JSONObject jsonObj = (JSONObject) obj;
                return new SaveData().checkIfIdExist(mid, jsonObj);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}