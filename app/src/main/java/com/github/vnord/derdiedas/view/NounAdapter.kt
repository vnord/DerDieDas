package com.github.vnord.derdiedas.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.databinding.NounItemBinding

class NounAdapter(private val onItemClicked: (Noun) -> Unit) :
    ListAdapter<Noun, NounAdapter.BusStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Noun>() {
            override fun areItemsTheSame(oldItem: Noun, newItem: Noun): Boolean {
                return oldItem.noun == newItem.noun
            }

            override fun areContentsTheSame(oldItem: Noun, newItem: Noun): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            NounItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    class BusStopViewHolder(private var binding: NounItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noun: Noun) {
            binding.genderTextView.text = noun.gender.str
            binding.nounTextView.text = noun.noun
        }
    }
}
