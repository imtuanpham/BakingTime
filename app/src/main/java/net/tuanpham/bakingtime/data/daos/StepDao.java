/*
    @author: Tuan Pham
    @since: 2018-08-16 20:58:41
 */

package net.tuanpham.bakingtime.data.daos;

/*
    @author: Tuan Pham
    @since: 2018-08-16 20:58:41
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import net.tuanpham.bakingtime.data.entities.Step;

import java.util.List;

@Dao
public interface StepDao {

    @Insert
    void insert(Step step);

    @Query("SELECT * from step WHERE recipe_id = :recipeId ORDER BY pid ASC")
    LiveData<List<Step>> getRecipeSteps(int recipeId);

    @Query("SELECT * from step WHERE pid = :stepId")
    LiveData<Step> getStep(int stepId);
}
