package net.tuanpham.bakingtime.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import net.tuanpham.bakingtime.data.BakingRoomDatabase;
import net.tuanpham.bakingtime.data.daos.RecipeDao;
import net.tuanpham.bakingtime.data.entities.Recipe;

import java.util.List;

public class RecipeRepository {

    private RecipeDao mRecipeDao;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeRepository(Application application) {
        BakingRoomDatabase db = BakingRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mAllRecipes = mRecipeDao.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }
}