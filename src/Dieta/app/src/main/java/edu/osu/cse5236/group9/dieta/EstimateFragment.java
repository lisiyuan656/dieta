package edu.osu.cse5236.group9.dieta;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;


public class EstimateFragment extends Fragment implements View.OnClickListener{
    Meal mealtoestimate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_estimate, container, false);

        View buttonConfirm=v.findViewById(R.id.estimate_confirm);
        buttonConfirm.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.estimate_confirm:
                Food testfood = new Food("apple");
                testfood.setEstimated_Weight(100);
                mealtoestimate.addFood(testfood);
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    try {
                        mealtoestimate.getFoods().get(0).FetchData();
                    } catch (IOException e) {

                    }
                } else {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Network connection not available.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                // startActivity(new Intent(getActivity(),ResultsActivity.class));
                break;
        }
    }
}
