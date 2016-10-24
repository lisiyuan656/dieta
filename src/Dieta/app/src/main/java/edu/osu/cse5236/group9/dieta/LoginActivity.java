package edu.osu.cse5236.group9.dieta;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends SingleFragmentActivity {
    private Button mButton_Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButton_Exit = (Button) findViewById(R.id.login_exit);
        mButton_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: save before exit
                finish();
            }
        });
    }

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
}
