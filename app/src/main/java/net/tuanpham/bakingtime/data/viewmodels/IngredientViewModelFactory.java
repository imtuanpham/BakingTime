package net.tuanpham.bakingtime.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

// A custom factory that allows create ViewModel with extra ID param
public class IngredientViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private int mId;

    public IngredientViewModelFactory(Application application, int id) {
        mApplication = application;
        mId = id;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new IngredientViewModel(mApplication, mId);
    }
}