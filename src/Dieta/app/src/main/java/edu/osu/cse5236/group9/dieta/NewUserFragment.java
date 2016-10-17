package edu.osu.cse5236.group9.dieta;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NewUserFragment extends Fragment {

    private Button mButton_Confirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_user, container, false);
        mButton_Confirm = (Button) v.findViewById(R.id.newuser_confirm);
        mButton_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // TODO: update User object
                if (true) {
                    Intent i = new Intent(getActivity(), NewFoodActivity.class);
                    startActivity(i);
                }
            }
        });
        return v;
    }
}