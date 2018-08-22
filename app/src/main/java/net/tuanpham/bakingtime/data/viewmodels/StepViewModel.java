package net.tuanpham.bakingtime.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import net.tuanpham.bakingtime.data.entities.Step;
import net.tuanpham.bakingtime.data.repositories.StepRepository;

import java.util.List;

public class StepViewModel extends AndroidViewModel {

    public static final String STEP_ID = "step_id";
    private final MutableLiveData<Integer> selectedStepId = new MutableLiveData<>();

    private StepRepository mRepository;

    public StepViewModel(Application application) {
        super(application);
        mRepository = new StepRepository(application);
    }

    public void selectStep(Integer stepId) {
        selectedStepId.setValue(stepId);
    }

    public LiveData<Integer> getSelectedStepId() {
        return selectedStepId;
    }

    public LiveData<Step> getStep(int stepId) {
        return mRepository.getStep(stepId);
    }

    public LiveData<List<Step>> getRecipeSteps(int recipeId) {
        return mRepository.getRecipeSteps(recipeId);
    }
}