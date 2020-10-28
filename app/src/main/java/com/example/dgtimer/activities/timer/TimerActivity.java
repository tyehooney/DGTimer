package com.example.dgtimer.activities.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dgtimer.R;
import com.example.dgtimer.activities.settings.SettingsActivity;
import com.example.dgtimer.databinding.ActivityTimerBinding;
import com.example.dgtimer.db.Capsule;
import com.google.android.gms.ads.AdRequest;

import java.util.List;

public class TimerActivity extends AppCompatActivity {

    private ActivityTimerBinding binding;
    private TimerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer);
        binding.setLifecycleOwner(this);

        Intent fromIntent = getIntent();
        int id = fromIntent.getIntExtra("id", 0);
        if (id != 0){
            viewModel = new ViewModelProvider(this, new TimerViewModel.Factory(getApplication(), id))
                    .get(TimerViewModel.class);

            binding.setViewModel(viewModel);

            viewModel.getLiveData().observe(this, new Observer<Capsule>() {
                @Override
                public void onChanged(Capsule capsule) {
                    viewModel.setTimes(capsule.getType(), capsule.getStage());
                }
            });

            viewModel.getTimes().observe(this, new Observer<List<CounterViewModel>>() {
                @Override
                public void onChanged(final List<CounterViewModel> counterViewModels) {
                    if (binding.llTimers.getChildCount() == 0){
                        for (int i = 0; i < counterViewModels.size(); i++) {
                            final CounterView counterView =
                                    new CounterView(TimerActivity.this, counterViewModels.get(i));
                            final int finalI = i;

                            counterView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    viewModel.onCounterViewClick(finalI);
                                }
                            });
                            binding.llTimers.addView(counterView);
                        }
                    }
                }
            });

            //배너 광고 개시
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.onStop();
    }

    public void goSettingsActivity(View view){
        startActivity(new Intent(this, SettingsActivity.class));
    }
}