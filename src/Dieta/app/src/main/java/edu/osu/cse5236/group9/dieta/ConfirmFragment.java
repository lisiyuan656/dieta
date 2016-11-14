package edu.osu.cse5236.group9.dieta;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;


public class ConfirmFragment extends Fragment {
    private Button mButton_Confirm;
    private ArrayList<String> mFoodList;
    private Meal mMeal;
    private HashMap<String, Boolean> mToggleButtonStateMap;

    private class FoodListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<String> AL_list = new ArrayList<String>();
        public FoodListAdapter(Context context, ArrayList<String> mFoodList) {
            mInflater = LayoutInflater.from(context);
            this.AL_list = mFoodList;
        }

        public long getItemId(int position) {
            return position;
        }

        public int getCount() {
            return AL_list.size();
        }

        public Object getItem(int position) {
            return AL_list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final String food_name = (String) getItem(position);
            if (convertView==null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.confirm_list_element, parent, false);
            }
            if (!mToggleButtonStateMap.containsKey(food_name)) {
                mToggleButtonStateMap.put(food_name, false);
            }
            ToggleButton food_button = (ToggleButton) convertView.findViewById(R.id.confirm_list_element);
            food_button.setText(food_name);
            food_button.setTextOn(food_name);
            food_button.setTextOff(food_name);
            food_button.setChecked(false);
            food_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mToggleButtonStateMap.put(food_name, isChecked);
                }
            });
            return convertView;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm, container, false);
        mToggleButtonStateMap = new HashMap<>();
        mFoodList = getActivity().getIntent().getStringArrayListExtra("mFoodList");
        mMeal = (Meal) getActivity().getIntent().getParcelableExtra("mMeal");
        FoodListAdapter adapter = new FoodListAdapter(getActivity(), mFoodList);
        final ListView mList_Confirm = (ListView) v.findViewById(R.id.confirm_list);
        mList_Confirm.setAdapter(adapter);
        mButton_Confirm = (Button) v.findViewById(R.id.confirm_food_list_button);
        mButton_Confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (String key : mToggleButtonStateMap.keySet()) {
                    if (mToggleButtonStateMap.get(key)) mMeal.addFood(new Food(key));
                }
                Intent i = new Intent(getActivity(), EstimateActivity.class);
                i.putExtra("mMeal", mMeal);
                startActivity(i);
            }
        });
        return v;
    }

}
