package net.tuanpham.bakingtime.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.BakingRoomDatabase;
import net.tuanpham.bakingtime.data.daos.IngredientDao;
import net.tuanpham.bakingtime.data.entities.Ingredient;

import java.util.List;

public class IngredientRepository {

    private IngredientDao mIngredientDao;

    public IngredientRepository(Application application) {
        BakingRoomDatabase db = BakingRoomDatabase.getDatabase(application);
        mIngredientDao = db.ingredientDao();
    }


    public LiveData<List<Ingredient>> getRecipeIngredients(int recipeId) {
        return mIngredientDao.getRecipeIngredients(recipeId);
    }
}