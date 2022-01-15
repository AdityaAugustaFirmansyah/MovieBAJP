package com.aditya.moviebajp.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.vo.Status
import com.aditya.moviebajp.databinding.ActivityDetailBinding
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.ui.detail.tv.DetailTvActivity
import com.aditya.moviebajp.viewmodel.DetailViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {
    private var isFavourite: Boolean = false
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    companion object {
        const val TAG: String = "tag-detail"
    }

    private lateinit var movieEntity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(
                intent.getIntExtra(TAG, 0),
                this
            )
        )[DetailMovieViewModel::class.java]

        viewModel.detailMovieLiveData().observe(this, { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        movieEntity = it
                        binding.apply {
                            Glide.with(this@DetailMovieActivity)
                                .load(ApiClient.BASE_URL_IMAGE + it.posterPath)
                                .error(R.drawable.ic_broken_image_black).into(posterDetail)

                            Glide.with(this@DetailMovieActivity)
                                .load(ApiClient.BASE_URL_IMAGE + it.backdropPath)
                                .error(R.drawable.ic_broken_image_black).into(backdrop)
                            tvTitleDetail.text = it.title
                            tvLanguageDetail.text = it.originalLanguage
                            tvDateDetail.text = it.releaseDate
                            tvAdultDetail.text =
                                if (it.adult) getString(R.string.yes) else getString(R.string.no)
                            tvPopularityDetail.text = it.popularity.toString()
                            tvTotalDetail.text = it.voteCount.toString()
                            tvAverageDetail.text = it.voteAverage.toString()
                            tvDescriptionDetail.text = it.overview
                            toolbar.title = it.title

                            isFavourite = it.favourite

                            menu?.findItem(R.id.favourite_menu_detail)?.icon =
                                AppCompatResources.getDrawable(
                                    binding.root.context,
                                    if (it.favourite)
                                        R.drawable.ic_baseline_favorite_24_white
                                    else
                                        R.drawable.ic_baseline_favorite_border_24
                                )
                        }
                    }
                    binding.content.visibility = View.VISIBLE
                    binding.appbar.visibility = View.VISIBLE
                    binding.progressBarDetail.visibility = View.GONE
                    DetailTvActivity.showError(binding, View.GONE, "${it.message}")
                }
                Status.FAILURE -> {
                    binding.content.visibility = View.INVISIBLE
                    binding.appbar.visibility = View.INVISIBLE
                    binding.progressBarDetail.visibility = View.GONE
                    DetailTvActivity.showError(binding, View.VISIBLE, "${it.message}")
                }
                Status.LOADING -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                    binding.content.visibility = View.INVISIBLE
                    binding.appbar.visibility = View.INVISIBLE
                    DetailTvActivity.showError(binding, View.GONE, "${it.message}")
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        menu?.findItem(R.id.favourite_menu_detail)?.icon =
            AppCompatResources.getDrawable(
                binding.root.context,
                if (isFavourite)
                    R.drawable.ic_baseline_favorite_24_white
                else
                    R.drawable.ic_baseline_favorite_border_24
            )
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder(this)
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_title))
                .setText(getString(R.string.share_text, movieEntity.title))
                .startChooser()
        } else if (item.itemId == R.id.favourite_menu_detail) {
            viewModel.setFavourite(movieEntity)
        }
        return true
    }
}