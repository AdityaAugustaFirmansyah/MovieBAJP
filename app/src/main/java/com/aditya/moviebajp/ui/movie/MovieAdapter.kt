package com.aditya.moviebajp.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.databinding.ItemMovieBinding
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.ui.detail.movie.DetailMovieActivity
import com.aditya.moviebajp.utils.MovieDummy
import com.bumptech.glide.Glide

class MovieAdapter(private val movieEntities: List<MovieEntity>) : RecyclerView.Adapter<MovieAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movieEntities[position])
    }

    override fun getItemCount(): Int = movieEntities.size

    class Holder(private val binding:ItemMovieBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            binding.apply {
                Glide.with(itemView).load(ApiClient.BASE_URL_IMAGE+movieEntity.poster_path).error(R.drawable.ic_broken_image_black).into(poster)
                ratingMovie.rating = (movieEntity.vote_average/2).toFloat()
                tvTitle.text = movieEntity.title
                languageMovie.text = movieEntity.original_language
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.TAG,movieEntity.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}