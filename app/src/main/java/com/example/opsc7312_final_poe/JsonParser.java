package com.example.opsc7312_final_poe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class JsonParser {
    private HashMap<String, String> parserJsonObject(JSONObject object) {
        ////Initialize hash map
        HashMap<String, String> dataList = new HashMap<>();
        try {
            //Get name from object
            String name = object.getString("name");
            //Get Latitude from object
            String latitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lat");
            //Get Latitude from object
            String longitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lng");

            //Put all value in hash
            dataList.put("name", name);
            dataList.put("lat", latitude);
            dataList.put("lng", longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private List<HashMap<String, String>> parseJsonArray(JSONArray jsonArray) {
        //Initialize hash map list
        List<HashMap<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                //Initialize hash map
                HashMap<String, String> data = parserJsonObject((JSONObject) jsonArray.get(i));
                //All data in hash map list
                dataList.add(data);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // Return hash map list
        return dataList;
    }

    List<HashMap<String, String>> parseResult(JSONObject object) {
        //Initialize json array
        JSONArray jsonArray = null;
        //Get results array
        try {
            jsonArray = object.getJSONArray("results");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseJsonArray(jsonArray);
        //return json parser class


    }
}
