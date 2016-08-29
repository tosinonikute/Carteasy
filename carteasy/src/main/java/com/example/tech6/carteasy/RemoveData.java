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
 * Created by tech6 on 11/2/15.
 */
public class RemoveData {


    public void RemoveDataByKey(String mid, String mkey, Context context){

        Boolean mkeyFound = false;

          /* Create a new JSON object items to store values */
        JSONObject items = new JSONObject();

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Carteasy.carteasyDirName, Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, Carteasy.carteasyFileName);

        if(mypath.exists()){

            JSONParser parser = new JSONParser();
            try {

                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;


                SaveData sd = new SaveData();

                /* Checks if both the ID and Key exist, if not print an Error message */
                if(sd.checkIfIdExist(mid, jsonObj)) {
                    if (sd.checkIfKeyExist(mid, mkey, jsonObj)) {

                        for (Object key : jsonObj.keySet()) {

                            //based on you key types
                            String keyStr = (String) key;
                            Object keyvalue = jsonObj.get(keyStr);


                            //for nested objects iteration if required
                            if(keyvalue instanceof JSONObject) {

                                JSONObject products = new JSONObject();

                                /* Loop the JSON object again for nested object */
                                JSONObject newJsonObj = (JSONObject) keyvalue;
                                for (Object key2 : newJsonObj.keySet()) {

                                    //based on you key types
                                    String keyStr2 = (String) key2;
                                    Object keyvalue2 = newJsonObj.get(keyStr2);

                                    if (keyStr.equals(mid)){ //product id
                                        if (keyStr2.equals(mkey)){ // product name
                                            mkeyFound = true;

                                            /* Notify the user that it has been updated */
                                            Log.d("Carteasy: ", mid + "=>" + mkey + "=>" + "removed");
                                        }
                                    }

                                    if(!mkeyFound.equals(true)) {
                                        products.put(keyStr2, keyvalue2);
                                    } else {
                                        mkeyFound = false;
                                    }
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


        } else {

            //Path does not exist
        }

    }



    /* Remove Data by Id */
    public Boolean RemoveDataById(String mid, Context context){

        Boolean removed = false;
        /* Create a new JSON object items to store values */
        JSONObject items = new JSONObject();

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Carteasy.carteasyDirName, Context.MODE_PRIVATE);

        // Create imageDir in applications default directory
        File mypath = new File(directory, Carteasy.carteasyFileName);

        if(mypath.exists()){

            JSONParser parser = new JSONParser();
            try {

                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;
                SaveData sd = new SaveData();

                /* Checks if both the ID and Key exist, if not print an Error message */
                if(sd.checkIfIdExist(mid, jsonObj)) {

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

                                //If this is true, It would not be added to the products JSONObject. So its removed
                                if (keyStr.equals(mid)) {

                                    removed = true; //If true, means ID and all pertaining data is removed

                                } else {
                                    products.put(keyStr2, keyValue2);
                                }
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

         /* Notify the user that it has been updated */
        if(removed.equals(true)) {
            Log.d("Carteasy: ", mid + "=>" + "=>removed");
        }

        return removed;
    }


    /* Remove Data all data in Json file */
    public void ClearAllData(Context context){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Carteasy.carteasyDirName, Context.MODE_PRIVATE);
        new File(directory, Carteasy.carteasyFileName).delete();
    }
}