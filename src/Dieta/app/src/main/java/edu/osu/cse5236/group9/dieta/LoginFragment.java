package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    private static final String FRAGMENTNAME = "LoginFragment";
    private EditText mEditText_Username;
    private EditText mEditText_Password;
    private Button mButton_Login;
    private Button mButton_NewUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(FRAGMENTNAME, "onCreate() called");
    }
    // TODO: Input Handlers

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        mButton_Login = (Button) v.findViewById(R.id.login_login);
        mButton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: authentication
                if (true) {
                    Intent i = new Intent(getActivity(), NewFoodActivity.class);
                    startActivity(i);
                }
            }
        });

        mButton_NewUser = (Button) v.findViewById(R.id.login_newuser);
        mButton_NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewUserActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(FRAGMENTNAME, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(FRAGMENTNAME, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(FRAGMENTNAME, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(FRAGMENTNAME, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(FRAGMENTNAME, "onDestroy() called");
    }
}
