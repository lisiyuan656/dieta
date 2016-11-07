package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class LoginFragment extends Fragment {
    private static final String FRAGMENTNAME = "LoginFragment";
    private Button mButton_Login;
    private Button mButton_NewUser;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        mButton_Login = (Button) v.findViewById(R.id.login_login);
        mButton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser()!=null) {
                    FirebaseUser mUser = mAuth.getCurrentUser();
                    Toast.makeText(getActivity(), "Welcome back! " + mUser.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), NewFoodActivity.class);
                    startActivity(i);
                }
                else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                                    .build(),
                            RC_SIGN_IN);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // user is signed in!
            startActivity(new Intent(getActivity(), NewFoodActivity.class));
            getActivity().finish();
            return;
        }
        /*
        // Sign in cancelled
        if (resultCode == RESULT_CANCELED) {

            return;
        }
        // No network
        if (resultCode == AddressConstants.ResultCodes) {
            showSnackbar(R.string.no_internet_connection);
            return;
        }
        */
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
