package edu.osu.cse5236.group9.dieta;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class ConfirmActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        FragmentManager fm = getSupportFragmentManager();
        Fragment confirm_fragment = fm.findFragmentById(R.id.confirm_fragment_container);
        if (confirm_fragment==null) {
            confirm_fragment = new ConfirmFragment();
            fm.beginTransaction()
                    .add(R.id.confirm_fragment_container, confirm_fragment)
                    .commit();
        }
    }
}
