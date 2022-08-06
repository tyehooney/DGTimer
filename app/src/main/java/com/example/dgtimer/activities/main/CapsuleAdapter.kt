package com.example.dgtimer.activities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dgtimer.R
import com.example.dgtimer.databinding.ItemCapsuleBinding
import com.example.dgtimer.db.Capsule

class KCapsuleAdapter(
    private val onItemClick: (Int) -> Unit,
    private val onStarClick: (Int) -> Unit
) : ListAdapter<Capsule, KCapsuleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KCapsuleViewHolder =
        KCapsuleViewHolder(
            ItemCapsuleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: KCapsuleViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, onStarClick)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Capsule>() {
            override fun areItemsTheSame(oldItem: Capsule, newItem: Capsule) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Capsule, newItem: Capsule) =
                oldItem == newItem
        }
    }
}

class KCapsuleViewHolder(
    private val binding: ItemCapsuleBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        capsule: Capsule,
        onItemClick: (Int) -> Unit,
        onStarClick: (Int) -> Unit
    ) {
        val context = itemView.context
        with(binding) {
            Glide.with(context)
                .load(capsule.image)
                .into(ivCapsule)
            tvCapsule.text = capsule.name
            ivBtnCapsuleMajor.setImageDrawable(
                if (capsule.isMajor) {
                    ContextCompat.getDrawable(context, R.drawable.ic_star_yellow)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.ic_star_gray)
                }
            )
            ivBtnCapsuleMajor.setOnClickListener {
                onStarClick.invoke(capsule.id)
            }
            vCapsuleColor.setBackgroundColor(capsule.colorAsInt)
            itemView.setOnClickListener {
                onItemClick.invoke(capsule.id)
            }
        }
    }
}