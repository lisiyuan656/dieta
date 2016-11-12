package edu.osu.cse5236.group9.dieta;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Siyuan on 10/29/16.
 */

public class Food implements Parcelable {
    private String mName;
    private double mEstimated_Weight;
    private double mCalories;
    private double mTotal_Fat;
    private double mSodium;
    private double mProtein;
    private double mCholesterol;
    private double mTotal_Carbohydrates;

    public Food(String name) {
        mName = name;
    }

    public boolean FetchData() {
        // TODO: add code to fetch data using nutritionix api
        return true;
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
