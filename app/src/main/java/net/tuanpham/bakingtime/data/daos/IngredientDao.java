/*
    @author: Tuan Pham
    @since: 2018-08-16 20:58:41
 */

package net.tuanpham.bakingtime.data.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import net.tuanpham.bakingtime.data.entities.Ingredient;
import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Query("SELECT * from ingredient WHERE recipe_id = :recipeId ORDER BY pid ASC")
    LiveData<List<Ingredient>> getRecipeIngredients(int recipeId);
}
