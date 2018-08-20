package net.tuanpham.bakingtime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.data.viewmodels.StepViewModel;
import net.tuanpham.bakingtime.fragments.StepFragment;

public class StepActivity extends AppCompatActivity {

    private int mStepId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Intent intent = getIntent();
        if (intent == null) {
            finish(); return;
        }

        mStepId = intent.getIntExtra(StepViewModel.STEP_ID, -1);
        if (mStepId == -1) {
            finish(); return;
        }

        // pass recipe ID from activity to fragment
        Bundle bundle = new Bundle();
        bundle.putInt(StepViewModel.STEP_ID, mStepId);

        // set Arguments
        FragmentManager fragmentManager = getSupportFragmentManager();
        StepFragment stepFragment = (StepFragment) fragmentManager.findFragmentById(R.id.fm_step);
        stepFragment.putArguments(bundle);

    }
}
