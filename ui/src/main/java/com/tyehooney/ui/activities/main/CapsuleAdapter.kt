package com.tyehooney.ui.activities.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tyehooney.domain.model.Capsule

class CapsuleAdapter(
    private val onItemClick: (Int) -> Unit,
    private val onStarClick: (Int) -> Unit
) : ListAdapter<Capsule, CapsuleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapsuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return CapsuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CapsuleViewHolder, position: Int) {
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

class CapsuleViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(android.R.id.text1)
    
    fun bind(
        capsule: Capsule,
        onItemClick: (Int) -> Unit,
        onStarClick: (Int) -> Unit
    ) {
        textView.text = capsule.name
        itemView.setOnClickListener {
            onItemClick.invoke(capsule.id)
        }
    }
}