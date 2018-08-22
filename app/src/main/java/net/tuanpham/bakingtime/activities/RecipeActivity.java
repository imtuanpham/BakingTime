package net.tuanpham.bakingtime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;
import net.tuanpham.bakingtime.data.viewmodels.StepViewModel;
import net.tuanpham.bakingtime.fragments.IngredientListFragment;
import net.tuanpham.bakingtime.fragments.RecipeFragment;
import net.tuanpham.bakingtime.fragments.StepFragment;

public class RecipeActivity extends AppCompatActivity
        implements RecipeFragment.OnIngredientsClickHandler, RecipeFragment.OnStepClickHandler {

    private final String LOG_TAG = RecipeActivity.class.getSimpleName();
    private int mRecipeId;
    private String mRecipeName;

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;
    private int mRightPaneMode;

    private static final String RIGHT_PANE_MODE = "RIGHT_PANE_MODE";
    private static final int RIGHT_PANE_MODE_STEP = 1;
    private static final int RIGHT_PANE_MODE_INGREDIENT_LIST = 2;

    private StepViewModel mStepViewModel;

    private StepFragment mStepFragment;
    private IngredientListFragment mIngredientListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        if (intent == null) {
            finish(); return;
        }

        mRecipeId = intent.getIntExtra(RecipeViewModel.RECIPE_ID, -1);
        if (mRecipeId == -1) {
            finish(); return;
        }

        mRecipeName = intent.getStringExtra(RecipeViewModel.RECIPE_NAME);
        if (mRecipeName != null)
            this.setTitle(mRecipeName);

        // pass recipe ID from activity to fragment
        Bundle bundle = new Bundle();
        bundle.putInt(RecipeViewModel.RECIPE_ID, mRecipeId);

        // set Arguments
        final FragmentManager fragmentManager = getSupportFragmentManager();
        RecipeFragment recipeFragment = (RecipeFragment) fragmentManager.findFragmentById(R.id.fm_recipe);
        recipeFragment.putArguments(bundle);

        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.vw_two_pane_divider) != null) {
            // This divider will only initially exist in the two-pane tablet case
            mTwoPane = true;
            mIngredientListFragment = new IngredientListFragment();
            mStepFragment = new StepFragment();

            int rightPaneMode;
            if(savedInstanceState != null) {
                rightPaneMode = savedInstanceState.getInt(RIGHT_PANE_MODE);
            } else {
                // default to the ingredients
                rightPaneMode = RIGHT_PANE_MODE_INGREDIENT_LIST;
            }

            switchRightPaneMode(rightPaneMode);

        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(RIGHT_PANE_MODE, mRightPaneMode);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onIngredientsClick() {
        if(mTwoPane) {
            switchRightPaneMode(RIGHT_PANE_MODE_INGREDIENT_LIST);
        } else {
            launchIngredientListActivity();
        }
    }

    @Override
    public void onStepClick(int stepId) {
        Log.d(LOG_TAG, "onStepClick " + String.valueOf(stepId));

        if(mTwoPane) {
            switchRightPaneMode(RIGHT_PANE_MODE_STEP);
        } else {
            launchStepActivity(stepId);
        }
    }

    private void switchRightPaneMode(int mode) {
        // do nothing if the right pane mode is unchanged
        if (mode == mRightPaneMode)
            return;

        mRightPaneMode = mode;

        final FragmentManager fragmentManager = getSupportFragmentManager();
        switch (mRightPaneMode) {
            case RIGHT_PANE_MODE_INGREDIENT_LIST:
                // pass recipe ID from activity to fragment
                Bundle bundle = new Bundle();
                bundle.putInt(RecipeViewModel.RECIPE_ID, mRecipeId);

                fragmentManager.beginTransaction()
                        .replace(R.id.ll_recipe_container, mIngredientListFragment)
                        .commit();

                mIngredientListFragment.putArguments(bundle);
                return;
            case RIGHT_PANE_MODE_STEP:
                fragmentManager.beginTransaction()
                        .replace(R.id.ll_recipe_container, mStepFragment)
                        .commit();
                return;
            default:
                return;
        }
    }

    private void launchStepActivity(int stepId) {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra(StepViewModel.STEP_ID, stepId);
        startActivity(intent);
    }

    private void launchIngredientListActivity() {
        Intent intent = new Intent(this, IngredientListActivity.class);
        intent.putExtra(RecipeViewModel.RECIPE_ID, mRecipeId);
        intent.putExtra(RecipeViewModel.RECIPE_NAME, mRecipeName);
        startActivity(intent);
    }

}
