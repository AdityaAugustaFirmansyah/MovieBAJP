package com.aditya.moviebajp.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.moviebajp.R

import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.databinding.ItemMovieBinding
import com.aditya.moviebajp.data.source.remote.network.ApiClient
import com.aditya.moviebajp.ui.detail.tv.DetailTvActivity
import com.bumptech.glide.Glide

class TvAdapter : PagedListAdapter<TvEntity,TvAdapter.Holder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Holder(private val binding:ItemMovieBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(tvEntity: TvEntity) {
            binding.apply {
                Glide.with(itemView).load(ApiClient.BASE_URL_IMAGE+tvEntity.posterPath).error(R.drawable.ic_broken_image_black).into(poster)
                ratingMovie.rating = (tvEntity.voteAverage/2).toFloat()
                tvTitle.text = tvEntity.name
                languageMovie.text = tvEntity.originalLanguage
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.TAG,tvEntity.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object:DiffUtil.ItemCallback<TvEntity>(){
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}