package com.example.dgtimer.activities.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.dgtimer.R;
import com.example.dgtimer.databinding.ActivitySettingsBinding;

import static com.example.dgtimer.ApplicationClass.mSharedPreferences;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SettingsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        viewModel.getVolume().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editor.putInt("volume", integer);
                editor.apply();
            }
        });

        viewModel.getAmplitude().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editor.putInt("amplitude", integer);
                editor.apply();
            }
        });
    }

    public void onFinishSettings(View view){
        finish();
    }
}