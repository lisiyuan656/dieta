package edu.osu.cse5236.group9.dieta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class NewFoodFragment extends Fragment {
    private EditText mNameField;
    private Button mButton_Camera;
    private Button mButton_AddFood;
    private Button mButton_Confirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_food, container, false);
        mNameField = (EditText) v.findViewById(R.id.food_name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO: Update Food object
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank
            }
        });
        mButton_Camera = (Button) v.findViewById(R.id.camera_button);
        mButton_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: camera stuff
            }
        });
        mButton_AddFood = (Button) v.findViewById(R.id.add_food_button);
        mButton_AddFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: update Food object
            }
        });
        mButton_Confirm = (Button) v.findViewById(R.id.confirm_food_button);
        mButton_Confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: update Food object && background operation
                Intent i = new Intent(getActivity(), ConfirmActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

}
