package com.github.vnord.derdiedas.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.vnord.derdiedas.data.NounPhrase
import com.github.vnord.derdiedas.databinding.NounPhraseItemBinding

class NounPhraseAdapter(private val onItemClicked: (NounPhrase) -> Unit) :
    ListAdapter<NounPhrase, NounPhraseAdapter.BusStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NounPhrase>() {
            override fun areItemsTheSame(oldItem: NounPhrase, newItem: NounPhrase): Boolean {
                return oldItem.noun == newItem.noun
            }

            override fun areContentsTheSame(oldItem: NounPhrase, newItem: NounPhrase): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            NounPhraseItemBinding.inflate(
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

    class BusStopViewHolder(private var binding: NounPhraseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nounPhrase: NounPhrase) {
            binding.genderTextView.text = nounPhrase.gender.str
            binding.nounPhraseTextView.text = nounPhrase.noun
        }
    }
}
