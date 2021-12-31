package com.aditya.moviebajp.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.TvEntity
import com.aditya.moviebajp.databinding.ItemMovieBinding
import com.aditya.moviebajp.ui.detail.tv.DetailTvActivity
import com.aditya.moviebajp.utils.MovieDummy
import com.bumptech.glide.Glide

class TvAdapter(private val tvEntities: List<TvEntity>) : RecyclerView.Adapter<TvAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(tvEntities[position])
    }

    override fun getItemCount(): Int = tvEntities.size

    class Holder(private val binding:ItemMovieBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(tvEntity: TvEntity) {
            binding.apply {
                Glide.with(itemView).load(MovieDummy.BASE_URL_IMAGE+tvEntity.poster_path).error(R.drawable.ic_broken_image_black).into(poster)
                ratingMovie.rating = (tvEntity.vote_average/2).toFloat()
                tvTitle.text = tvEntity.name
                languageMovie.text = tvEntity.original_language
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.TAG,adapterPosition)
                itemView.context.startActivity(intent)
            }
        }
    }
}