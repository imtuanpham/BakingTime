package net.tuanpham.bakingtime;


import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import net.tuanpham.bakingtime.activities.IngredientListActivity;
import net.tuanpham.bakingtime.activities.RecipeActivity;
import net.tuanpham.bakingtime.activities.RecipeListActivity;
import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityBasicTest {
    private final String LOG_TAG = RecipeListActivityBasicTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityRule =
            new ActivityTestRule<>(RecipeListActivity.class, false, false);

    private int mRecipeId;

    @Test
    public void loadData_Click_ToActivity() {
        mActivityRule.launchActivity(null);

        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.rv_recipe_list);
        int recipeCount = recyclerView.getAdapter().getItemCount();

        Intents.init();

        onView(withId(R.id.rv_recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        intended(hasComponent(RecipeActivity.class.getName()));

    }

}
