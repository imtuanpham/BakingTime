package net.tuanpham.bakingtime.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    public static final String RECIPE_ID = "recipe_id";
    public static final String RECIPE_NAME = "recipe_name";

    private RecipeRepository mRepository;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mRepository.getAllRecipes();
    }

    public LiveData<Recipe> getRecipe(int recipeId) {
        return mRepository.getRecipe(recipeId);
    }
}
