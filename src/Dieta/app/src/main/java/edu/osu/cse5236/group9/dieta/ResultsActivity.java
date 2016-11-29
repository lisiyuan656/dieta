package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

public class ResultsActivity extends FragmentActivity implements View.OnClickListener {
    private static final String ACTIVITYNAME = "ResultsActivity";
    private Meal mMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ACTIVITYNAME, "onCreate(Bundle) called");
        setContentView(R.layout.activity_results);

        mMeal=getIntent().getParcelableExtra("mMeal");
        Food Total_nutrition = sumUp(mMeal);

        ResultsFragment resultsFragment= new ResultsFragment();
        resultsFragment.passFood(Total_nutrition);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.results_nfacts,resultsFragment);
        fragmentTransaction.commit();

        View buttonDetails=findViewById(R.id.results_detail);
        View buttonFinish=findViewById(R.id.results_finish);
        buttonDetails.setOnClickListener(this);
        buttonFinish.setOnClickListener(this);
    }


    // Calculate total nutritional facts
    private Food sumUp(Meal input) {
        Double Calories = 0.0;
        Double Total_Fat = 0.0;
        Double Sodium = 0.0;
        Double Protein = 0.0;
        Double Cholesterol = 0.0;
        Double Total_Carbohydrates = 0.0;
        for (Food curfood : input.getFoods()) {
            Calories+=curfood.getCalories();
            Total_Fat+=curfood.getTotal_Fat();
            Sodium+=curfood.getSodium();
            Protein+=curfood.getProtein();
            Cholesterol+=curfood.getCholesterol();
            Total_Carbohydrates+=curfood.getTotal_Carbohydrates();
        }
        Food Total_nutrition = new Food("Total nutrition");
        Total_nutrition.setCalories(Calories);
        Total_nutrition.setTotal_Fat(Total_Fat);
        Total_nutrition.setSodium(Sodium);
        Total_nutrition.setProtein(Protein);
        Total_nutrition.setCholesterol(Cholesterol);
        Total_nutrition.setTotal_Carbohydrates(Total_Carbohydrates);
        return Total_nutrition;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.results_detail:
                Intent i= new Intent(this,DetailedResultsActivity.class);
                i.putExtra("mMeal",mMeal);
                startActivity(i);
                break;
            case R.id.results_finish:
                startActivity(new Intent(this,NewFoodActivity.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(ACTIVITYNAME, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(ACTIVITYNAME, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(ACTIVITYNAME, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(ACTIVITYNAME, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITYNAME, "onDestroy() called");
    }

}
