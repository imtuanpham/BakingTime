package net.tuanpham.bakingtime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;
import net.tuanpham.bakingtime.fragments.IngredientListFragment;

public class IngredientListActivity extends AppCompatActivity {

    private int mRecipeId;
    private String mRecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }

        mRecipeId = intent.getIntExtra(RecipeViewModel.RECIPE_ID, -1);
        if (mRecipeId == -1) {
            finish();
            return;
        }

        mRecipeName = intent.getStringExtra(RecipeViewModel.RECIPE_NAME);
        if (mRecipeName != null)
            this.setTitle(mRecipeName);


        // pass recipe ID from activity to fragment
        Bundle bundle = new Bundle();
        bundle.putInt(RecipeViewModel.RECIPE_ID, mRecipeId);

        // set Arguments
        IngredientListFragment fragment = new IngredientListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment.putArguments(bundle);

        // fragment needs to be created dynamically so putArgments occurs BEFORE the fragment view is created
        fragmentManager.beginTransaction()
                .replace(R.id.ll_ingredient_list_container, fragment)
                .commit();
    }
}