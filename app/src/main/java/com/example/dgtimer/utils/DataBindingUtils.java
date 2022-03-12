package com.example.dgtimer.utils;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.dgtimer.R;
import com.example.dgtimer.activities.main.CapsuleAdapter;

public class DataBindingUtils {

//    recyclerView adapter
    @BindingAdapter({"adapter"})
    public static void setAdapter(RecyclerView view, CapsuleAdapter adapter){
        view.setAdapter(adapter);
    }

//    검색 키보드
    @BindingAdapter({"setFocus"})
    public static void setFocus(final EditText view, boolean focus){
        final InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if (focus){
            view.setVisibility(View.VISIBLE);
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }else{
            view.setText("");
            view.clearFocus();
            view.setVisibility(View.GONE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    view.clearFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return true;
            }
        });
    }

//    검색 버튼 상태 변경
    @BindingAdapter({"setAnim"})
    public static void setAnimation(ImageView view, boolean on){
        AnimatedVectorDrawableCompat avdc;
        AnimatedVectorDrawable avd;

        if (on){
            view.setImageDrawable(view.getContext().getDrawable(R.drawable.avd_anim_search_to_back));
        }else{
            view.setImageDrawable(view.getContext().getDrawable(R.drawable.avd_anim_back_to_search));
        }

        Drawable drawable = view.getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat){
            avdc = (AnimatedVectorDrawableCompat) drawable;
            avdc.start();
        }else if(drawable instanceof  AnimatedVectorDrawable){
            avd = (AnimatedVectorDrawable) drawable;
            avd.start();
        }
    }
}
