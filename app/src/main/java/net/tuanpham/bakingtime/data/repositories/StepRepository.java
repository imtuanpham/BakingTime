package net.tuanpham.bakingtime.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import net.tuanpham.bakingtime.data.BakingRoomDatabase;
import net.tuanpham.bakingtime.data.daos.StepDao;
import net.tuanpham.bakingtime.data.entities.Step;

import java.util.List;

public class StepRepository {

    private StepDao mStepDao;
    private LiveData<List<Step>> mRecipeSteps;
    private LiveData<Step> mStep;

    public StepRepository(Application application) {
        BakingRoomDatabase db = BakingRoomDatabase.getDatabase(application);
        mStepDao = db.stepDao();
    }

    public void selectRecipeId(int recipeId) {
        mRecipeSteps = mStepDao.getRecipeSteps(recipeId);
    }

    public void selectStep(int stepId) {
        mStep = mStepDao.getStep(stepId);
    }

    public LiveData<List<Step>> getRecipeSteps() {
        return mRecipeSteps;
    }

    public LiveData<Step> getSelectedStep() {
        return mStep;
    }
}