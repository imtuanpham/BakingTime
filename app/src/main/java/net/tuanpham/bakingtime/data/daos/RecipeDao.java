/*
    @author: Tuan Pham
    @since: 2018-08-16 20:58:41
 */

package net.tuanpham.bakingtime.data.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import net.tuanpham.bakingtime.data.entities.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe recipe);

    @Query("DELETE FROM recipe")
    void deleteAll();

    @Query("SELECT * from recipe ORDER BY recipe_id ASC")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT * from recipe WHERE recipe_id = :recipeId")
    LiveData<Recipe> getRecipe(int recipeId);

    @Query("SELECT count(*) rec_count from recipe")
    int getRecipeCount();
}