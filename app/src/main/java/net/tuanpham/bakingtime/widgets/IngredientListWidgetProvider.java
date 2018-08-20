package net.tuanpham.bakingtime.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.activities.RecipeActivity;
import net.tuanpham.bakingtime.activities.RecipeListActivity;
import net.tuanpham.bakingtime.data.viewmodels.IngredientViewModel;
import net.tuanpham.bakingtime.data.viewmodels.RecipeViewModel;

// Reference: https://medium.com/android-bits/android-widgets-ad3d166458d3
public class IngredientListWidgetProvider extends AppWidgetProvider {

    private final String LOG_TAG = IngredientListWidgetProvider.class.getSimpleName();

    private int mRecipeId = -1;
    private String mIngredientListDisplay;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                 int appWidgetId) {

        Log.d(LOG_TAG, "updateAppWidget");

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list);

        Intent intent = new Intent(context, RecipeActivity.class);
        if(mRecipeId == -1) {
            intent = new Intent(context, RecipeListActivity.class);
        } else {
            intent = new Intent(context, RecipeActivity.class);
            intent.putExtra(RecipeViewModel.RECIPE_ID, mRecipeId);
            views.setTextViewText(R.id.tv_widget_ingredient_list, mIngredientListDisplay);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.tv_widget_ingredient_list, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");

        if(intent.hasExtra(RecipeViewModel.RECIPE_ID)) {
            mRecipeId = intent.getIntExtra(RecipeViewModel.RECIPE_ID, -1);
            Log.d(LOG_TAG, "recipeID " + String.valueOf(mRecipeId));
        }

        if(intent.hasExtra(IngredientViewModel.INGREDIENT_LIST)) {
            mIngredientListDisplay = intent.getStringExtra(IngredientViewModel.INGREDIENT_LIST);
            Log.d(LOG_TAG, "list: " + mIngredientListDisplay);
        }

        super.onReceive(context, intent);
    }
}
