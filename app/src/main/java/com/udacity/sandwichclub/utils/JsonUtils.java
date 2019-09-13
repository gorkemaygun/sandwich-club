package com.udacity.sandwichclub.utils;




import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        if (json==null||json.isEmpty()){

            return null;
        }
            try {

                JSONObject get_text = new JSONObject(json);
                JSONObject nameJsonObject= get_text.getJSONObject("name");
                String MainName=nameJsonObject.getString("mainName");
                JSONArray alsoknownasJsonArray= get_text.getJSONArray("alsoKnownAs");
                ArrayList <String> alsoKnownAsList= new ArrayList<>();
                if (alsoknownasJsonArray!=null){

                    for (int i=0;i<=alsoknownasJsonArray.length();i++){
                        alsoKnownAsList.add(alsoknownasJsonArray.getString(i));
                    }
                }
                String Description=(get_text.getString("description"));
                String PlaceOfOrigin=(get_text.getString("placeOfOrigin"));
                String Image=(get_text.getString("image"));
                JSONArray ingredientsArray  =get_text.getJSONArray("ingredients");
                ArrayList <String> ingredientslist=new ArrayList<>();
                if (ingredientsArray!=null){

                    for (int i=0;i<=ingredientsArray.length();i++){
                        ingredientslist.add(ingredientsArray.getString(i));
                    }

                }
                return new Sandwich(MainName,alsoKnownAsList,PlaceOfOrigin,Description,Image,ingredientslist);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

