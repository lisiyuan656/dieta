package edu.osu.cse5236.group9.dieta;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class NewUserActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        FragmentManager fm = getSupportFragmentManager();
        Fragment newuser_fragment = fm.findFragmentById(R.id.new_user_fragment_container);
        if (newuser_fragment==null) {
            newuser_fragment = new NewUserFragment();
            fm.beginTransaction()
                    .add(R.id.new_user_fragment_container, newuser_fragment)
                    .commit();
        }
    }
}
