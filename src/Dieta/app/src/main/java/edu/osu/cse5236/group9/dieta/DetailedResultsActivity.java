package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailedResultsActivity extends FragmentActivity implements View.OnClickListener{
    private static final String ACTIVITYNAME = "DetailedResultsActivity";
    private Meal mMeal;
    private int currentIndex;
    private int mealSize;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ACTIVITYNAME, "onCreate(Bundle) called");
        setContentView(R.layout.activity_detailed_results);

        // TODO: get meal from prior class
        mMeal=getIntent().getParcelableExtra("mMeal");
        mealSize=mMeal.getFoods().size();
        if(savedInstanceState!=null) {
            currentIndex = savedInstanceState.getInt("curIndex");
        } else {
            currentIndex=0;
        }

        ResultsFragment resultsFragment= new ResultsFragment();
        if (mealSize > 0) {
            resultsFragment.passFood(mMeal.getFoods().get(currentIndex));
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.detailedresults_nfacts,resultsFragment);
        fragmentTransaction.commit();

        View buttonLeft=findViewById(R.id.detailedresults_left);
        View buttonRight=findViewById(R.id.detailedresults_right);
        View buttonFinish=findViewById(R.id.detailedresults_finish);
        mTextView=(TextView) findViewById(R.id.textView_foodname);
        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        buttonFinish.setOnClickListener(this);
        if (mealSize>0) {
            mTextView.setText(mMeal.getFoods().get(currentIndex).getName());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("curIndex",currentIndex);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detailedresults_left:
                if (currentIndex>0) {
                    currentIndex--;
                    if(getSupportFragmentManager().findFragmentById(R.id.detailedresults_nfacts) != null) {
                        ResultsFragment resultsFragment= new ResultsFragment();
                        resultsFragment.passFood(mMeal.getFoods().get(currentIndex));
                        mTextView.setText(mMeal.getFoods().get(currentIndex).getName());
                        getSupportFragmentManager().beginTransaction().replace(R.id.detailedresults_nfacts,resultsFragment).commit();
                    }
                }
                break;
            case R.id.detailedresults_right:
                if (currentIndex<mealSize-1) {
                    currentIndex++;
                    if(getSupportFragmentManager().findFragmentById(R.id.detailedresults_nfacts) != null) {
                        ResultsFragment resultsFragment= new ResultsFragment();
                        resultsFragment.passFood(mMeal.getFoods().get(currentIndex));
                        mTextView.setText(mMeal.getFoods().get(currentIndex).getName());
                        getSupportFragmentManager().beginTransaction().replace(R.id.detailedresults_nfacts,resultsFragment).commit();
                    }
                }
                break;
            case R.id.detailedresults_finish:
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
