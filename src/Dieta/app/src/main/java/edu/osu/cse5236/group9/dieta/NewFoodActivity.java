package edu.osu.cse5236.group9.dieta;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class NewFoodActivity extends FragmentActivity {
    private static final String ACTIVITYNAME = "NewFoodActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ACTIVITYNAME, "onCreate(Bundle) called");
        setContentView(R.layout.activity_new_food);

        FragmentManager fm = getSupportFragmentManager();
        Fragment newfood_fragment = fm.findFragmentById(R.id.new_food_fragment_container);
        if (newfood_fragment==null) {
            newfood_fragment = new NewFoodFragment();
            fm.beginTransaction()
                    .add(R.id.new_food_fragment_container, newfood_fragment)
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(ACTIVITYNAME, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(ACTIVITYNAME, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(ACTIVITYNAME, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(ACTIVITYNAME, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITYNAME, "onDestroy() called");
    }
}
