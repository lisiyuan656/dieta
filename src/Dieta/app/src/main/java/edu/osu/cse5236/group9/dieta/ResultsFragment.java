package edu.osu.cse5236.group9.dieta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {
    private Food mFood;


    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // TODO: get mFood

        // TODO: add fact numbers

        View v= inflater.inflate(R.layout.fragment_results, container, false);

        View text_calories = v.findViewById(R.id.textView_calories);
        View text_totalfat = v.findViewById(R.id.textView_totalfat);


        return v;
    }

}
