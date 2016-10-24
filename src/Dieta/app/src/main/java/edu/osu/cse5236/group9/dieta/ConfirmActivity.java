package edu.osu.cse5236.group9.dieta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

public class ConfirmActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ConfirmFragment();
    }


}
