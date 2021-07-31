package com.example.dgtimer.activities.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.dgtimer.AppRater;
import com.example.dgtimer.R;
import com.example.dgtimer.databinding.ActivityMainBinding;
import com.example.dgtimer.db.Capsule;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new MainViewModel.Factory(getApplication(), this))
                .get(MainViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        //scroll 될 때 fab 보이기
        binding.rvCapsules.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                viewModel.setScrollY(viewModel.getScrollY().getValue()+dy);
            }
        });

        viewModel.getCapsules().observe(this, new Observer<List<Capsule>>() {
            @Override
            public void onChanged(List<Capsule> capsules) {
                Parcelable recyclerViewState = binding.rvCapsules.getLayoutManager().onSaveInstanceState();
                viewModel.refreshData((CapsuleAdapter)binding.rvCapsules.getAdapter(), capsules);
                if(recyclerViewState != null) binding.rvCapsules.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            }
        });

        viewModel.getSearchingWord().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.search(s);
            }
        });
    }

    //fab onClick
    public void scrollUp(View view){
        binding.rvCapsules.smoothScrollToPosition(0);
    }

    //끝내기 전에 평가 dialog 띄우기
    @Override
    public void onBackPressed() {
        AppRater.set(this);
    }
}