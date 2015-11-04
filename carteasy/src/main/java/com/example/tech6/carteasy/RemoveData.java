package com.example.tech6.carteasy;

import android.content.Context;
import android.content.ContextWrapper;

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
            File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);

            // Create imageDir in applications default directory
            File mypath = new File(directory, "test.json");

            if(mypath.exists()){

                JSONParser parser = new JSONParser();
                try {

                    Object obj = parser.parse(new FileReader(mypath));
                    JSONObject jsonObj = (JSONObject) obj;
                    System.out.println("hellokey");


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
                                                System.out.println(mid + "=>" + mkey + "=>" + "removed");
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
                            System.out.println("Key does not exist");
                        }
                    } else {
                        System.out.println("ID does not exist");
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
    public void RemoveDataById(String mid, Context context){

        Boolean removed = false;
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


                SaveData sd = new SaveData();

                    /* Checks if both the ID and Key exist, if not print an Error message */
                if(sd.checkIfIdExist(mid, jsonObj)) {

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

                                    //If this is true, It would not be added to the products JSONObject. So its removed
                                    if (keyStr.equals(mid)) {

                                        removed = true; //If true, means ID and all pertaining data is removed

                                    } else {
                                        products.put(keyStr2, keyvalue2);
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
                        System.out.println("Key does not exist");
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
            System.out.println(mid + "=>" + "=>removed");
        }

    }


    /* Remove Data by Id */
    public void ClearAllData(){

    }
}
