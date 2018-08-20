package net.tuanpham.bakingtime.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    public static final String RECIPE_ID = "recipe_id";

    private RecipeRepository mRepository;

    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mAllRecipes = mRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }
}
