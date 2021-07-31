package com.example.dgtimer.activities.main;

import androidx.recyclerview.widget.DiffUtil;

import com.example.dgtimer.db.Capsule;

import java.util.List;

public class CapsuleDiffCallback extends DiffUtil.Callback {

    private final List<CapsuleItemViewModel> oldCapsules;
    private final List<CapsuleItemViewModel> newCapsules;

    CapsuleDiffCallback(List<CapsuleItemViewModel> oldData, List<CapsuleItemViewModel> newData){
        oldCapsules = oldData;
        newCapsules = newData;
    }

    @Override
    public int getOldListSize() {
        return oldCapsules.size();
    }

    @Override
    public int getNewListSize() {
        return newCapsules.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCapsules.get(oldItemPosition).getId() == newCapsules.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCapsules.get(oldItemPosition).getName().equals(newCapsules.get(newItemPosition).getName());
    }
}
