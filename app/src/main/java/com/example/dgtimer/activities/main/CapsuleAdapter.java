package com.example.dgtimer.activities.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dgtimer.databinding.ItemCapsuleBinding;

import java.util.List;

public class CapsuleAdapter extends RecyclerView.Adapter<CapsuleAdapter.CapsuleHolder> {

    private Context mContext;
    private List<CapsuleItemViewModel> mDataViewModels;
    private LayoutInflater inflater;

    public CapsuleAdapter(Context context, List<CapsuleItemViewModel> dataViewModels){
        this.mContext = context;
        this.mDataViewModels = dataViewModels;
    }

    class CapsuleHolder extends RecyclerView.ViewHolder{
        private ItemCapsuleBinding binding;

        public CapsuleHolder(ItemCapsuleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CapsuleItemViewModel viewModel){
            this.binding.setViewModel(viewModel);
            this.binding.setLifecycleOwner((LifecycleOwner) mContext);
        }

        public ItemCapsuleBinding getBinding(){
            return this.binding;
        }
    }

    @NonNull
    @Override
    public CapsuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(mContext);
        }
        ItemCapsuleBinding capsulesBinding = ItemCapsuleBinding.inflate(inflater, parent, false);
        return new CapsuleHolder(capsulesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final CapsuleHolder holder, int position) {
        final CapsuleItemViewModel capsule = mDataViewModels.get(position);

        holder.bind(capsule);

        //TimerActivity 이동
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capsule.onItemClicked(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataViewModels.size();
    }

    //LiveData 자동 갱신
    public void setData(List<CapsuleItemViewModel> dataViewModels){
        CapsuleDiffCallback diffCallback = new CapsuleDiffCallback(mDataViewModels, dataViewModels);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        mDataViewModels = dataViewModels;

        diffResult.dispatchUpdatesTo(this);
    }
}
