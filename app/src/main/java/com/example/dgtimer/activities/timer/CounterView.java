package com.example.dgtimer.activities.timer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.lifecycle.LifecycleOwner;

import com.example.dgtimer.databinding.ViewCounterBinding;

public class CounterView extends LinearLayout {

    private CounterViewModel viewModel;
    ViewCounterBinding binding;

    public CounterView(Context context, CounterViewModel viewModel) {
        super(context);
        this.viewModel = viewModel;
        init(context);
    }

    public CounterView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public CounterViewModel getViewModel(){return this.viewModel;}

    private void init(Context context){
        binding = ViewCounterBinding.inflate(LayoutInflater.from(context), this, true);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner((LifecycleOwner) context);
    }
}
