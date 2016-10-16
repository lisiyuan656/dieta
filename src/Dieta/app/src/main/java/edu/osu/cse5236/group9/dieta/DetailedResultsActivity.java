package edu.osu.cse5236.group9.dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class DetailedResultsActivity extends FragmentActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_results);

        ResultsFragment resultsFragment= new ResultsFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.result_nfacts,resultsFragment);
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

                break;
            case R.id.detailedresults_right:

                break;
            case R.id.detailedresults_finish:
                startActivity(new Intent(this,NewFoodActivity.class));
                break;
        }
    }
}
