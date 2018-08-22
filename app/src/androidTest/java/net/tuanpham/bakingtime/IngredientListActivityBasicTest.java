package net.tuanpham.bakingtime;


import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import net.tuanpham.bakingtime.activities.IngredientListActivity;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IngredientListActivityBasicTest {
    private final String LOG_TAG = IngredientListActivityBasicTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<IngredientListActivity> mActivityRule =
            new ActivityTestRule<>(IngredientListActivity.class, false, false);

    private int mRecipeId;

    @Test
    public void loadDataFromIntent() {
        mRecipeId = 1;
        Intent i = new Intent();
        i.putExtra(RecipeViewModel.RECIPE_ID, mRecipeId);
        mActivityRule.launchActivity(i);

        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.rv_ingredient_list);
        int ingredientCount = recyclerView.getAdapter().getItemCount();
        Log.d(LOG_TAG, "ingredientCount = " + String.valueOf(ingredientCount));


        if (ingredientCount > 0){
            onView(withId(R.id.rv_ingredient_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        }

    }
}
