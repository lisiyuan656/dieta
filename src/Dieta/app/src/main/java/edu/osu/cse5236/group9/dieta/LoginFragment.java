package edu.osu.cse5236.group9.dieta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    private EditText mEditText_Username;
    private EditText mEditText_Password;
    private Button mButton_Login;
    private Button mButton_Exit;
    private Button mButton_NewUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            }
        });
        mButton_Exit = (Button) v.findViewById(R.id.login_exit);
        mButton_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: save before exit
                // TODO: Exit the activity
            }
        });
        mButton_NewUser = (Button) v.findViewById(R.id.login_newuser);
        mButton_NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: jump to NewUserActivity
            }
        });
        return v;
    }
}
