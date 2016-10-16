package edu.osu.cse5236.group9.dieta;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class NewFoodActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
