package net.tuanpham.bakingtime.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.BakingRoomDatabase;
import net.tuanpham.bakingtime.data.daos.IngredientDao;
import net.tuanpham.bakingtime.data.entities.Ingredient;

import java.util.List;

public class IngredientRepository {

    private int mRecipeId;
    private IngredientDao mIngredientDao;
    private LiveData<List<Ingredient>> mRecipeIngredients;

    public IngredientRepository(Application application) {
        BakingRoomDatabase db = BakingRoomDatabase.getDatabase(application);
        mIngredientDao = db.ingredientDao();
    }

    public void selectRecipeId(int recipeId) {
        mRecipeIngredients = mIngredientDao.getRecipeIngredients(recipeId);
    }

    public LiveData<List<Ingredient>> getRecipeIngredients() {
        return mRecipeIngredients;
    }
}