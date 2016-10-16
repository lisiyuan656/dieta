package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

public class ResultsActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ResultsFragment resultsFragment= new ResultsFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.results_nfacts,resultsFragment);
        fragmentTransaction.commit();

        View buttonDetails=findViewById(R.id.results_detail);
        View buttonFinish=findViewById(R.id.results_finish);
        buttonDetails.setOnClickListener(this);
        buttonFinish.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.results_detail:
                startActivity(new Intent(this,DetailedResultsActivity.class));
                break;
            case R.id.results_finish:
                startActivity(new Intent(this,NewFoodActivity.class));
                break;
        }
    }

}
