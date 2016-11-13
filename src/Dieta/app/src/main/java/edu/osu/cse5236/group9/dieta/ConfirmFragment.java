package edu.osu.cse5236.group9.dieta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class ConfirmFragment extends Fragment {
    private Button mButton_Confirm;
    private List<String> mFoodList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm, container, false);
        mFoodList = getActivity().getIntent().getStringArrayListExtra("mFoodList");
        mButton_Confirm = (Button) v.findViewById(R.id.confirm_food_list_button);
        mButton_Confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: update food object
                Intent i = new Intent(getActivity(), EstimateActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

}
