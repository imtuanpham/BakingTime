package net.tuanpham.bakingtime.activities;
/*
    @author: Tuan Pham
    @since: 2018-08-18 11:13:18
 */

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.adapters.RecipeListAdapter;
import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements RecipeListAdapter.OnRecipeClickHandler {

    private final String LOG_TAG = RecipeListActivity.class.getSimpleName();

    private RecipeViewModel mRecipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        RecyclerView rvRecipeList = findViewById(R.id.rv_recipe_list);
        final RecipeListAdapter adapter = new RecipeListAdapter(this);
        rvRecipeList.setAdapter(adapter);
        rvRecipeList.setLayoutManager(new LinearLayoutManager(this));

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
            // Update the cached copy of the recipes in the adapter.
            adapter.setRecipes(recipes);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        launchRecipeActivity(recipe);
    }

    private void launchRecipeActivity(Recipe recipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra(RecipeViewModel.RECIPE_ID, recipe.getRecipeId());
        intent.putExtra(RecipeViewModel.RECIPE_NAME, recipe.getName());
        startActivity(intent);
    }
}
