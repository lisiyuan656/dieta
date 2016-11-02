package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends FragmentActivity {
    private Button mButton_Exit;
    private Button mButton_Signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "onCreate(Bundle) called");
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.login_fragment_container);
        if (fragment==null) {
            fragment = new LoginFragment();
            fragmentManager.beginTransaction().add(R.id.login_fragment_container, fragment).commit();
        }
        mButton_Signout = (Button) findViewById(R.id.sign_out_button);
        mButton_Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(LoginActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                Toast.makeText(LoginActivity.this, "See you!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
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
    public void onStart() {
        super.onStart();
        Log.d(this.getClass().getSimpleName(), "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(this.getClass().getSimpleName(), "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(this.getClass().getSimpleName(), "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(this.getClass().getSimpleName(), "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(this.getClass().getSimpleName(), "onDestroy() called");
    }
}
