package edu.osu.cse5236.group9.dieta;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class EstimateFragment extends Fragment implements View.OnClickListener{
    private Meal mMeal;
    ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_estimate, container, false);

        View buttonConfirm=v.findViewById(R.id.estimate_confirm);
        buttonConfirm.setOnClickListener(this);
        mMeal=new Meal();

        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.estimate_confirm:
                Food testfood = new Food("apple");
                testfood.setEstimated_Weight(100);
                mMeal.addFood(testfood);
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadNutritionFacts().execute("");
                    Log.d("","");
                } else {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Network connection not available.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
        }
    }


    private class DownloadNutritionFacts extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(getActivity());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {

            try {
                mMeal.estimateMeal();


            } catch (Exception e) {
                Log.d("downloadNutritionFacts","EstimateFail");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            startActivity(new Intent(getActivity(),ResultsActivity.class));
        }
    }
}
