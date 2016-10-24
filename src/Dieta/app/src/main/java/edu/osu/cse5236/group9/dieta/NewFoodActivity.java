package edu.osu.cse5236.group9.dieta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class NewFoodActivity extends SingleFragmentActivity {

    @Override
    protected  Fragment createFragment() {
        return new NewFoodFragment();
    }
}
