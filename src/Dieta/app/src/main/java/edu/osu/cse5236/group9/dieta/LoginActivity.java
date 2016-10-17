package edu.osu.cse5236.group9.dieta;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends FragmentActivity {
    private Button mButton_Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        Fragment login_fragment = fm.findFragmentById(R.id.login_fragment_container);
        if (login_fragment==null) {
            login_fragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.login_fragment_container, login_fragment)
                    .commit();
        }
        mButton_Exit = (Button) findViewById(R.id.login_exit);
        mButton_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: save before exit
                finish();
            }
        });
    }
}
