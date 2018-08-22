package net.tuanpham.bakingtime.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tuanpham.bakingtime.R;

import net.tuanpham.bakingtime.adapters.StepListAdapter;
import net.tuanpham.bakingtime.data.entities.Step;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;
import net.tuanpham.bakingtime.data.viewmodels.StepViewModel;

import java.util.List;


// This fragment displays summary of a recipe including links to ingredients and steps
public class RecipeFragment extends Fragment implements StepListAdapter.OnStepClickHandler {

    private final String LOG_TAG = RecipeFragment.class.getSimpleName();

    private StepViewModel mStepViewModel;
    private int mRecipeId;

    private OnStepClickHandler mStepClickHandler;

    public interface OnStepClickHandler {
        public void onStepClick(int stepId);
    }

    // Mandatory empty constructor
    public RecipeFragment() {
    }

    public void putArguments(Bundle args) {
        mRecipeId = args.getInt(RecipeViewModel.RECIPE_ID);
        Log.d(LOG_TAG, "recipeId: " + String.valueOf(mRecipeId));
        populateData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        TextView ingredientsLabel = rootView.findViewById(R.id.tv_ingredients_label);

        ingredientsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIngredientClickHandler.onIngredientsClick();
            }
        });


        // Return the root view
        return rootView;
    }

    private void populateData() {

        final View rootView = this.getView();
        RecyclerView rvStepList = rootView.findViewById(R.id.rv_step_list);
        final StepListAdapter adapter = new StepListAdapter(this);
        rvStepList.setAdapter(adapter);
        rvStepList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mStepViewModel = ViewModelProviders.of(this.getActivity()).get(StepViewModel.class);
        mStepViewModel.getRecipeSteps(mRecipeId).observe(this, new Observer<List<Step>>() {
            @Override
            public void onChanged(@Nullable final List<Step> steps) {
                // Update the cached copy of the steps in the adapter.
                adapter.setSteps(steps);
            }
        });

    }

    @Override
    public void onStepClick(int stepId) {
        Log.d(LOG_TAG, "stepId: " + String.valueOf(stepId));
        // this select step communicates with the StepFragment
        mStepViewModel.selectStep((Integer) stepId);

        // this handler communicates with the RecipeActivity
        mStepClickHandler.onStepClick(stepId);
    }

    private OnIngredientsClickHandler mIngredientClickHandler;

    public interface OnIngredientsClickHandler {
        public void onIngredientsClick();
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the onClick interface
        // If not, it throws an exception
        try {
            mIngredientClickHandler = (OnIngredientsClickHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIngredientsClickHandler");
        }

        try {
            mStepClickHandler = (OnStepClickHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickHandler");
        }
    }

}
