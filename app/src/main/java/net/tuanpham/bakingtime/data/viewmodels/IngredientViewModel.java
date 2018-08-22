package net.tuanpham.bakingtime.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.entities.Ingredient;
import net.tuanpham.bakingtime.data.repositories.IngredientRepository;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    public final static String INGREDIENT_LIST = "INGREDIENT_LIST";

    private IngredientRepository mRepository;

    public IngredientViewModel(Application application) {
        super(application);
        mRepository = new IngredientRepository(application);
    }

    public LiveData<List<Ingredient>> getRecipeIngredients(int recipeId) {
        return mRepository.getRecipeIngredients(recipeId);
    }
}