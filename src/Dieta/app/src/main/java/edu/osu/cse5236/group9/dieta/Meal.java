package edu.osu.cse5236.group9.dieta;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Meal implements Parcelable {
    private List<Food> mFoods;
    public void addFood(Food food) {
        mFoods.add(food);
    }
    private Meal(Parcel in) {
        this.mFoods = new ArrayList<>();
        in.readTypedList(mFoods, Food.CREATOR);
    }
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mFoods);
    }

    public static final Parcelable.Creator<Meal> CREATOR
            = new Parcelable.Creator<Meal>() {
        public Meal createFromParcel(Parcel in){
            return new Meal(in);
        }
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public boolean estimateMeal() {
        for (Food curfood : mFoods) {
            try{
                if(!curfood.FetchData()) {
                    return false;
                }
            } catch (Exception e){
                Log.d("setFoodNutritionFact","Exception");
                return false;
            }
        }
        return true;
    }

    public List<Food> getFoods() {
        return mFoods;
    }

    public void setFoods(List<Food> foods) {
        mFoods = foods;
    }

    public Meal() {
        mFoods=new ArrayList<Food>();
    }
}
