package com.example.dgtimer.activities.main;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dgtimer.db.Capsule;

import java.util.ArrayList;
import java.util.List;

import static com.example.dgtimer.ApplicationClass.capsuleDatabase;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Capsule>> capsules;
    private CapsuleAdapter rvAdapter, searchRvAdapter;
    private MutableLiveData<Integer> scrollY;
    private MutableLiveData<Boolean> searchOn = new MutableLiveData<>();
    private MutableLiveData<Boolean> searchRvOn = new MutableLiveData<>();
    private MutableLiveData<String> searchingWord = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application, Context context) {
        super(application);
        capsules = capsuleDatabase.getCapsuleDao().getAll();
        rvAdapter = new CapsuleAdapter(context, setItemViewModels(new ArrayList<Capsule>()));
        searchRvAdapter = new CapsuleAdapter(context, setItemViewModels(new ArrayList<Capsule>()));
        searchOn.setValue(searchOn.getValue() != null && searchOn.getValue());
        searchRvOn.setValue(searchRvOn.getValue() != null && searchRvOn.getValue());
    }

    public LiveData<List<Capsule>> getCapsules() {
        return capsules;
    }

    public LiveData<Integer> getScrollY(){
        if (scrollY == null){
            scrollY = new MutableLiveData<>();
            scrollY.setValue(0);
        }

        return scrollY;
    }

    public void setScrollY(int scrollY){this.scrollY.setValue(scrollY);}

    public MutableLiveData<Boolean> getSearchOn() {
        return searchOn;
    }

    public MutableLiveData<Boolean> getSearchRvOn() {
        return searchRvOn;
    }

    public MutableLiveData<String> getSearchingWord() {
        return searchingWord;
    }

    public List<CapsuleItemViewModel> setItemViewModels(List<Capsule> capsules){
        List<CapsuleItemViewModel> itemViewModels = new ArrayList<>();
        for (Capsule capsule : capsules)
            itemViewModels.add(new CapsuleItemViewModel(capsule));

        return itemViewModels;
    }

    public CapsuleAdapter getRvAdapter() {
        return rvAdapter;
    }

    public CapsuleAdapter getSearchRvAdapter() {
        return searchRvAdapter;
    }

    public void refreshData(CapsuleAdapter adapter, List<Capsule> capsuleList){
        adapter.setData(setItemViewModels(capsuleList));
    }

    public void onSearchBtnClick(){
        searchOn.setValue(!searchOn.getValue());
    }

    public void onSearchTextClearBtnClick(){
        searchingWord.setValue("");
    }

    public void search(@Nullable String str){
        if (str == null || str.isEmpty()){
            searchRvOn.setValue(false);
        }else{
            searchRvOn.setValue(true);
            refreshData(searchRvAdapter, getSearchResults(str));
        }
    }

    private List<Capsule> getSearchResults(String str){
        String[] strArr = str.split(" ");
        List<Capsule> resultList = new ArrayList<>();
        for (Capsule capsule : capsules.getValue()){
            boolean contains = true;
            for (String word : strArr){
                if (!capsule.getName().contains(word)) {
                    contains = false;
                    break;
                }
            }
            if (contains)
                resultList.add(capsule);
        }
        return resultList;
    }

    //ViewModelFactory
    static class Factory extends ViewModelProvider.AndroidViewModelFactory {
        private Application mApplication;
        private Context mContext;

        public Factory(@NonNull Application application, Context context) {
            super(application);
            mApplication = application;
            mContext = context;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MainViewModel(mApplication, mContext);
        }
    }
}
