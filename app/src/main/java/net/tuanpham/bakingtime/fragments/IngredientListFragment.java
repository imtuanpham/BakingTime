package net.tuanpham.bakingtime.fragments;

import android.app.Activity;
import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.adapters.IngredientListAdapter;
import net.tuanpham.bakingtime.data.entities.Ingredient;
import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.viewmodels.IngredientViewModel;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;
import net.tuanpham.bakingtime.widgets.IngredientListWidgetProvider;

import java.util.List;

public class IngredientListFragment extends Fragment implements IngredientListAdapter.IngredientOnClickHandler {

    private final String LOG_TAG = IngredientListFragment.class.getSimpleName();

    private IngredientViewModel mIngredientViewModel;
    private RecipeViewModel mRecipeViewModel;
    private int mRecipeId;
    private String mIngredientListDisplay;
    private Recipe mRecipe;

    // Mandatory empty constructor
    public IngredientListFragment() {
    }

    // method that allows activity to transfer data to fragment
    public void putArguments(Bundle args) {
        mRecipeId = args.getInt(RecipeViewModel.RECIPE_ID);
        Log.d(LOG_TAG, "putArguments");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(LOG_TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_ingredient_list, container, false);

        RecyclerView rvIngredientList = rootView.findViewById(R.id.rv_ingredient_list);
        final IngredientListAdapter adapter = new IngredientListAdapter(this);
        rvIngredientList.setAdapter(adapter);
        rvIngredientList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mIngredientViewModel = ViewModelProviders.of(this.getActivity()).get(IngredientViewModel.class);

        mIngredientViewModel.getRecipeIngredients(mRecipeId).observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(@Nullable final List<Ingredient> ingredients) {
                // Update the cached copy of the ingredients in the adapter.
                adapter.setIngredients(ingredients);
                mIngredientListDisplay = getIngredientListDisplay(ingredients);
            }
        });

        mRecipeViewModel = ViewModelProviders.of(this.getActivity()).get(RecipeViewModel.class);

        mRecipeViewModel.getRecipe(mRecipeId).observe(this, new Observer<Recipe>() {
            @Override
            public void onChanged(@Nullable final Recipe recipe) {
                mRecipe = recipe;
            }
        });

        final Button addToWidget = rootView.findViewById(R.id.btn_add_to_widget);
        addToWidget.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateWidget();
                        Toast.makeText(getContext(), R.string.added_to_widget, Toast.LENGTH_LONG).show();
                    }
                });

        // Return the root view
        return rootView;
    }

    void updateWidget() {
        // sending data to the widget
        Intent intent = new Intent(this.getActivity(), IngredientListWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        Activity thisActivity = this.getActivity();
        Application thisApp = thisActivity.getApplication();

        int[] ids = AppWidgetManager.getInstance(thisApp)
                .getAppWidgetIds(new ComponentName(thisApp, IngredientListWidgetProvider.class)
        );

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        intent.putExtra(RecipeViewModel.RECIPE_ID, mRecipeId);
        intent.putExtra(RecipeViewModel.RECIPE_NAME, mRecipe.getName());
        intent.putExtra(IngredientViewModel.INGREDIENT_LIST, mIngredientListDisplay);
        thisActivity.sendBroadcast(intent);
    }

    @Override
    public void onClickIngredient(int ingredientId) {
        // do nothing
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public String getIngredientListDisplay(List<Ingredient> ingredients) {
        String disp = "Ingredient List: ";
        for(Ingredient ingredient : ingredients) {
            disp = new StringBuilder()
                    .append(disp)
                    .append(ingredient.getIngredientDisplay())
                    .append("; ")
                    .toString();
        }
        return disp;
    }

}
