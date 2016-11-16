package edu.osu.cse5236.group9.dieta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {
    private Food mFood;


    public ResultsFragment() {
        // Required empty public constructor
    }


    public void passFood(Food inputFood) {
        mFood=inputFood;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v= inflater.inflate(R.layout.fragment_results, container, false);

        if (mFood!=null) {
            TextView text_calories = (TextView) v.findViewById(R.id.textView_calories);
            TextView text_totalfat = (TextView) v.findViewById(R.id.textView_totalfat);
            TextView text_sodium = (TextView) v.findViewById(R.id.textView_sodium);
            TextView text_protein = (TextView) v.findViewById(R.id.textView_protein);
            TextView text_cholesterol = (TextView) v.findViewById(R.id.textView_cholesterol);
            TextView text_totalcarbohydrates = (TextView) v.findViewById(R.id.textView_totalcarbohydrates);
            text_calories.setText(String.format("%.1f",mFood.getCalories()));
            text_totalfat.setText(String.format("%.1f",mFood.getTotal_Fat()));
            text_sodium.setText(String.format("%.1f",mFood.getSodium()));
            text_protein.setText(String.format("%.1f",mFood.getProtein()));
            text_cholesterol.setText(String.format("%.1f",mFood.getCholesterol()));
            text_totalcarbohydrates.setText(String.format("%.1f",mFood.getTotal_Carbohydrates()));
        }



        return v;
    }

}
