package com.example.cyjga.miniappone;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by cyjga on 2018-02-12.
 */



/*
public class Movie {


    public String title;
    public String episode_number;
    public ArrayList<String> main_characters;
    public String description;
    public String poster;
    public String url;

    // method
    // static methods that read the json file in and load into Movie

    // static method that loads our recipes.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Movie Object for each movie in JSON
        // add the object to arraylist
        // return arraylist
        try{
//            String jsonString = loadJsonFromAsset("movies.json",context);
            JSONObject json = new JSONObject(loadJsonFromAsset("movies.json",context));
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each movie in your movies array

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                JSONObject movieAtIndex = movies.getJSONObject(i);

                movie.title = movieAtIndex.getString("title");
                movie.episode_number = movieAtIndex.getString("episode_number");
                movie.description = movieAtIndex.getString("description");
                movie.url = movieAtIndex.getString("url");
                movie.poster = movieAtIndex.getString("poster");

                JSONArray mchar = movieAtIndex.getJSONArray("main_characters");
                for(int j=0;j<mchar.length();j++) {
//                    movie.main_characters.add(movieAtIndex.getJSONArray("main_characters").getString(j));
                    movie.main_characters.add(mchar.get(j).toString());
                }
                // add to arraylist
                movieList.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
*/



public class Movie {


    public String title;
    public String episode_number;
    public String main_characters;
    public String description;
    public String poster;
    public String url;
    public int has_seen = 0;
//    public String hasSeen;

    // method
    // static methods that read the json file in and load into Movie

    // static method that loads our recipes.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Movie Object for each movie in JSON
        // add the object to arraylist
        // return arraylist
        try{
//            String jsonString = loadJsonFromAsset("movies.json",context);
            JSONObject json = new JSONObject(loadJsonFromAsset("movies.json",context));
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each movie in your movies array

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                JSONObject movieAtIndex = movies.getJSONObject(i);

                movie.title = movieAtIndex.getString("title");
                movie.episode_number = movieAtIndex.getString("episode_number");
                movie.description = movieAtIndex.getString("description");
                movie.url = movieAtIndex.getString("url");
                movie.poster = movieAtIndex.getString("poster");

                JSONArray mchar = movieAtIndex.getJSONArray("main_characters");
                int mcharlength = mchar.length();
                movie.main_characters = "";
                if(mcharlength>3)
                    mcharlength = 3;
                for(int j=0;j<mcharlength;j++) {
                    movie.main_characters = movie.main_characters + mchar.getString(j);
                    if(j<mcharlength-1)
                        movie.main_characters = movie.main_characters + ", ";
                }
                // add to arraylist
                movieList.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public void updateMovieSeenStatus(int status) { has_seen = status; }

    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
