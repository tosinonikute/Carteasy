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
 *
 * Save function checkes if file exist and then save data to file in current apps directory
 *
 * UpdateValue function iterates through the existing data and updates a value based on its key and id
 *
 * checkIfExist checks if value exist before inserting, if exist do not save data.
 */
public class SaveData {

    public String save(Context context, JSONObject items, String id){

        String returnvalue = "data not saved";

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("carteasy", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "test.json");

        if(mypath.exists()){
            System.out.println("Exist");
            JSONParser parser = new JSONParser();
            try {

                Object obj = parser.parse(new FileReader(mypath));
                JSONObject jsonObj = (JSONObject) obj;
                if(!checkIfExist(id, jsonObj)) {
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

                    //Push to file
                    FileWriter filez = new FileWriter(mypath);
                    filez.write(items.toJSONString());
                    filez.flush();
                    filez.close();

                    returnvalue = "data saved";

                } else {

                    returnvalue = id+" exist";
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


    public void updateValue(String mid, String mkey, String newvalue, JSONObject jsonObj){


    }

    public Boolean checkIfExist(String id, JSONObject jsonObj) {

        Boolean exist = false;
                for (Object key : jsonObj.keySet()) {
                    //based on you key types
                    String keyStr = (String) key;
                    Object keyvalue = jsonObj.get(keyStr);

                    if (keyvalue instanceof JSONObject) {
                        if (keyvalue.equals(id)) {
                            exist = true;
                        }
                    }
                }
        return exist;
    }





}
