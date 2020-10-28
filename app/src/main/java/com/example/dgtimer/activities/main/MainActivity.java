package com.example.dgtimer.activities.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.dgtimer.AppRater;
import com.example.dgtimer.R;
import com.example.dgtimer.databinding.ActivityMainBinding;
import com.example.dgtimer.db.Capsule;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

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

        //광고 초기화
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

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
                viewModel.refreshData((CapsuleAdapter)binding.rvCapsules.getAdapter(), capsules);
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