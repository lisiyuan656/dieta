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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EstimateFragment extends Fragment implements View.OnClickListener{
    private Meal mMeal;
    private ProgressDialog pd;
    private ListView mListView;
    private class MealAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<Food> AL_list = new ArrayList<Food>();

        public MealAdapter(Context context, Meal meal) {
            mInflater = LayoutInflater.from(context);
            this.AL_list = (ArrayList<Food>) meal.getFoods();
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
            final Food food = (Food) getItem(position);
            if (convertView==null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.estimate_list_element, parent, false);
            }
            TextView food_name_text = (TextView) convertView.findViewById(R.id.estimate_element_food_name);
            food_name_text.setText("Food name: "+food.getName());
            final TextView food_weight_text = (TextView) convertView.findViewById(R.id.estimate_element_food_weight);
            food_weight_text.setText("Weight: "+String.format("%.1f", food.getEstimated_Weight())+" g");
            SeekBar estimate_seekbar = (SeekBar) convertView.findViewById(R.id.estimate_element_seekbar);
            estimate_seekbar.setMax(10000);
            Double barPosition = 0.0;
            if (food.getEstimated_Weight()!=0) {
                barPosition = food.getEstimated_Weight()*10;
            }
            estimate_seekbar.setProgress(barPosition.intValue());
            estimate_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    food_weight_text.setText("Weight: "+String.format("%.1f", progress/10.0)+" g");
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // blank
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    food.setEstimated_Weight(seekBar.getProgress()/10.0);
                }
            });

            return convertView;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_estimate, container, false);

        View buttonConfirm = v.findViewById(R.id.estimate_confirm);
        buttonConfirm.setOnClickListener(this);


        mMeal = getActivity().getIntent().getParcelableExtra("mMeal");
        MealAdapter adapter = new MealAdapter(getActivity(), mMeal);
        mListView = (ListView) v.findViewById(R.id.estimate_list);
        mListView.setAdapter(adapter);
        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.estimate_confirm:
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadNutritionFacts().execute("");
                    Log.d("EstimateFragment","Estimate Completed");
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
                if(!mMeal.estimateMeal()) {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Data fetching failed. Please try again.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

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
            Intent i = new Intent(getActivity(), ResultsActivity.class);
            i.putExtra("mMeal", mMeal);
            startActivity(i);
        }
    }
}
