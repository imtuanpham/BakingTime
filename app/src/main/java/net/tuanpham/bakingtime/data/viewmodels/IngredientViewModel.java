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
    private int mRecipeId;

    private LiveData<List<Ingredient>> mRecipeIngredients;

    public IngredientViewModel(Application application, int id) {
        super(application);
        mRecipeId = id;

        mRepository = new IngredientRepository(application);
        mRepository.selectRecipeId(mRecipeId);
        mRecipeIngredients = mRepository.getRecipeIngredients();
    }

    public LiveData<List<Ingredient>> getRecipeIngredients() {
        return mRecipeIngredients;
    }
}