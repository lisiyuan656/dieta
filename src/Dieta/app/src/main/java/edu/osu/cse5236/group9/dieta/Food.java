package edu.osu.cse5236.group9.dieta;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Siyuan on 10/29/16.
 */

public class Food implements Parcelable {
    private static final String sNutritionix_Address = "https://api.nutritionix.com/v1_1/search/";
    private String mName;
    private double mEstimated_Weight;
    private double mCalories;
    private double mTotal_Fat;
    private double mSodium;
    private double mProtein;
    private double mCholesterol;
    private double mTotal_Carbohydrates;

    private void setFoodNutritionFacts(JsonReader reader) {
        try {
            reader.beginObject();
            if(!reader.nextName().equals("total_hits")) {
                Log.d("reader","Fetch fail");
                return;
            }
            if(reader.nextInt()==0) {
                Log.d("reader","No matching");
                return;
            }
            while(reader.hasNext() && !reader.nextName().equals("hits")) {
                reader.skipValue();
            }
            reader.beginArray();
            reader.beginObject();
            while(reader.hasNext() && !reader.nextName().equals("fields")) {
                reader.skipValue();
            }
            reader.beginObject();;
            while(reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("nf_calories")) {
                    this.setCalories(reader.nextDouble());
                } else if (name.equals("nf_total_fat")) {
                    this.setTotal_Fat(reader.nextDouble());
                } else if (name.equals("nf_cholesterol")) {
                    this.setCholesterol(reader.nextDouble());
                } else if (name.equals("nf_sodium")) {
                    this.setSodium(reader.nextDouble());
                } else if (name.equals("nf_total_carbohydrate")) {
                    this.setTotal_Carbohydrates(reader.nextDouble());
                } else if (name.equals("nf_protein")) {
                    this.setProtein(reader.nextDouble());
                } else {
                    reader.skipValue();
                }
            }
        } catch (Exception e) {
            Log.d("setFoodNutritionFact","Exception");
        }
    }

    public Food(String name) {
        mName = name;
    }

    public boolean FetchData() throws IOException {
        // TODO: add code to fetch data using nutritionix api
        InputStream is = null;
        int maxconnnum=5;
        int curconnnum=0;
        boolean successconn=false;
        while (!successconn && curconnnum<maxconnnum) {
            try {
                String urlstring = "https://api.nutritionix.com/v1_1/search/";
                urlstring += getName().replace(" ","%20").trim();
                urlstring += "?fields=item_name,nf_calories,nf_total_fat,nf_sodium,nf_protein,nf_cholesterol,nf_total_carbohydrate&appId=fa7ccaab&appKey=7f717e06cc847e5e78f5b6a9407f18b9";
                URL url = new URL(urlstring);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d("FoodNutritionFetch", "The response is: " + response);
                if (response!=200) {
                    curconnnum++;
                    continue;
                }
                else {
                    successconn=true;
                }
                is = conn.getInputStream();

                JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
                setFoodNutritionFacts(reader);
                if (is != null) is.close();
                conn.disconnect();
                return true;


                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } catch (Exception e) {
                Log.d("FoodNutritionFetch","Exception");
            }
        }

        return false;
    }

    public double getEstimated_Weight() {
        return mEstimated_Weight;
    }

    public void setEstimated_Weight(double estimated_Weight) {
        mEstimated_Weight = estimated_Weight;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getCalories() {
        return mCalories;
    }

    public void setCalories(double calories) {
        mCalories = calories;
    }

    public double getTotal_Fat() {
        return mTotal_Fat;
    }

    public void setTotal_Fat(double total_Fat) {
        mTotal_Fat = total_Fat;
    }

    public double getSodium() {
        return mSodium;
    }

    public void setSodium(double sodium) {
        mSodium = sodium;
    }

    public double getProtein() {
        return mProtein;
    }

    public void setProtein(double protein) {
        mProtein = protein;
    }

    public double getCholesterol() {
        return mCholesterol;
    }

    public void setCholesterol(double cholesterol) {
        mCholesterol = cholesterol;
    }

    public double getTotal_Carbohydrates() {
        return mTotal_Carbohydrates;
    }

    public void setTotal_Carbohydrates(double total_Carbohydrates) {
        mTotal_Carbohydrates = total_Carbohydrates;
    }

    public int describeContents() {
        return 0;
    }

    private Food(Parcel in) {
        this.mName = in.readString();
        this.mEstimated_Weight = in.readDouble();
        this.mCalories = in.readDouble();
        this.mTotal_Fat = in.readDouble();
        this.mSodium = in.readDouble();
        this.mProtein = in.readDouble();
        this.mCholesterol = in.readDouble();
        this.mTotal_Carbohydrates = in.readDouble();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeDouble(mEstimated_Weight);
        dest.writeDouble(mCalories);
        dest.writeDouble(mTotal_Fat);
        dest.writeDouble(mSodium);
        dest.writeDouble(mProtein);
        dest.writeDouble(mCholesterol);
        dest.writeDouble(mTotal_Carbohydrates);
    }

    public static final Parcelable.Creator<Food> CREATOR
            = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
