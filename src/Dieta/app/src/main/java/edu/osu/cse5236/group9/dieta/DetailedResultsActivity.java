package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

public class DetailedResultsActivity extends FragmentActivity implements View.OnClickListener{
    private static final String ACTIVITYNAME = "DetailedResultsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ACTIVITYNAME, "onCreate(Bundle) called");
        setContentView(R.layout.activity_detailed_results);

        ResultsFragment resultsFragment= new ResultsFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.detailedresults_nfacts,resultsFragment);
        fragmentTransaction.commit();

        View buttonLeft=findViewById(R.id.detailedresults_left);
        View buttonRight=findViewById(R.id.detailedresults_right);
        View buttonFinish=findViewById(R.id.detailedresults_finish);
        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        buttonFinish.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detailedresults_left:
                // TODO: left
                break;
            case R.id.detailedresults_right:
                // TODO: right
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
