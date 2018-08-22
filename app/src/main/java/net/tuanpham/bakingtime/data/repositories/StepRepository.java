package net.tuanpham.bakingtime.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.BakingRoomDatabase;
import net.tuanpham.bakingtime.data.daos.StepDao;
import net.tuanpham.bakingtime.data.entities.Step;

import java.util.List;

public class StepRepository {

    private StepDao mStepDao;

    public StepRepository(Application application) {
        BakingRoomDatabase db = BakingRoomDatabase.getDatabase(application);
        mStepDao = db.stepDao();
    }

    public LiveData<List<Step>> getRecipeSteps(int recipeId) {
        return mStepDao.getRecipeSteps(recipeId);
    }

    public LiveData<Step> getStep(int stepId) {
        return mStepDao.getStep(stepId);
    }
}