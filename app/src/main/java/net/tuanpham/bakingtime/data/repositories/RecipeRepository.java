package net.tuanpham.bakingtime.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import net.tuanpham.bakingtime.data.BakingRoomDatabase;
import net.tuanpham.bakingtime.data.daos.RecipeDao;
import net.tuanpham.bakingtime.data.entities.Recipe;

import java.util.List;

public class RecipeRepository {

    private final String LOG_TAG = RecipeRepository.class.getSimpleName();

    private RecipeDao mRecipeDao;

    public RecipeRepository(Application application) {
        BakingRoomDatabase db = BakingRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        Log.d(LOG_TAG, "mRecipeDao.getAllRecipes");
        return mRecipeDao.getAllRecipes();
    }

    public LiveData<Recipe> getRecipe(int recipeId) {
        return mRecipeDao.getRecipe(recipeId);
    }
}