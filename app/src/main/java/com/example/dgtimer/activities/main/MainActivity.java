package com.example.dgtimer.activities.main;

import static com.example.dgtimer.ApplicationClass.refreshData;

import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dgtimer.AppRater;
import com.example.dgtimer.R;
import com.example.dgtimer.databinding.ActivityMainBinding;
import com.example.dgtimer.db.Capsule;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ConnectivityManager cm;
    private ConnectivityManager.NetworkCallback networkCallback;

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
                viewModel.setScrollY(viewModel.getScrollY().getValue() + dy);
            }
        });

        viewModel.getCapsules().observe(this, new Observer<List<Capsule>>() {
            @Override
            public void onChanged(List<Capsule> capsules) {
                Parcelable recyclerViewState = binding.rvCapsules.getLayoutManager().onSaveInstanceState();
                viewModel.refreshData((CapsuleAdapter) binding.rvCapsules.getAdapter(), capsules);
                if (recyclerViewState != null)
                    binding.rvCapsules.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            }
        });

        viewModel.getSearchingWord().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewModel.search(s);
            }
        });

        setNetworkCallback();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {
                Rect searchRect = new Rect();
                binding.llSearch.getGlobalVisibleRect(searchRect);
                if (!searchRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    view.clearFocus();
                    hideKeyboard(view);
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setNetworkCallback() {
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                refreshData();
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
            }
        };
        cm.registerDefaultNetworkCallback(networkCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cm != null && networkCallback != null) {
            cm.unregisterNetworkCallback(networkCallback);
        }
    }

    //fab onClick
    public void scrollUp(View view) {
        binding.rvCapsules.smoothScrollToPosition(0);
    }

    //끝내기 전에 평가 dialog 띄우기
    @Override
    public void onBackPressed() {
        if (viewModel.getSearchOn().getValue()) {
            binding.etSearch.setText("");
            viewModel.getSearchOn().setValue(false);
        } else {
            AppRater.set(this);
        }
    }
}