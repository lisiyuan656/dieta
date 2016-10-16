package edu.osu.cse5236.group9.dieta;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        Fragment login_fragment = fm.findFragmentById(R.id.login_fragment_container);
        if (login_fragment==null) {
            login_fragment = new NewFoodFragment();
            fm.beginTransaction()
                    .add(R.id.login_fragment_container, login_fragment)
                    .commit();
        }
    }
}
