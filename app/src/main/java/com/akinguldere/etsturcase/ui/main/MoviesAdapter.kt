package com.akinguldere.etsturcase.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akinguldere.etsturcase.databinding.ItemMovieBinding
import com.akinguldere.etsturcase.model.MovieItem

class MoviesAdapter :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(
            oldItem: MovieItem,
            newItem: MovieItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieItem,
            newItem: MovieItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val movie = differ.currentList[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MoviesViewHolder(
        private val binding: ItemMovieBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MovieItem) {

            binding.apply {
                movie = movieItem
                executePendingBindings()

                cardView.setOnClickListener {
                    onItemClickListener?.let {
                        it(movieItem)
                    }
                }

            }
        }
    }

    private var onItemClickListener: ((MovieItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieItem) -> Unit) {
        onItemClickListener = listener
    }

}