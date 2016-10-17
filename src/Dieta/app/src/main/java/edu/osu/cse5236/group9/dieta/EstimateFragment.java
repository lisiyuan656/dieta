package edu.osu.cse5236.group9.dieta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EstimateFragment extends Fragment implements View.OnClickListener{

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
                startActivity(new Intent(getActivity(),ResultsActivity.class));
                break;
        }
    }
}
