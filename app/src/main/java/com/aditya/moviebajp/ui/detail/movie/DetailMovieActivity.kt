package com.aditya.moviebajp.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.databinding.ActivityDetailBinding
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.ui.detail.tv.DetailTvActivity
import com.aditya.moviebajp.viewmodel.DetailViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val TAG: String = "tag-detail"
    }

    private lateinit var movieEntity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(
                intent.getIntExtra(TAG, 0),
                ApiClient.restApi()
            )
        )[DetailMovieViewModel::class.java]

        viewModel.detailMovieLiveData().observe(this, { it ->
            when (it.viewState) {
                ViewState.SUCCESS -> {
                    it.response?.let {
                        movieEntity = it
                        binding.apply {
                            Glide.with(this@DetailMovieActivity)
                                .load(ApiClient.BASE_URL_IMAGE + it.poster_path)
                                .error(R.drawable.ic_broken_image_black).into(posterDetail)

                            Glide.with(this@DetailMovieActivity)
                                .load(ApiClient.BASE_URL_IMAGE + it.backdrop_path)
                                .error(R.drawable.ic_broken_image_black).into(backdrop)
                            tvTitleDetail.text = it.title
                            tvLanguageDetail.text = it.original_language
                            tvDateDetail.text = it.release_date
                            tvAdultDetail.text =
                                if (it.adult) getString(R.string.yes) else getString(R.string.no)
                            tvPopularityDetail.text = it.popularity.toString()
                            tvTotalDetail.text = it.vote_count.toString()
                            tvAvarageDetail.text = it.vote_average.toString()
                            tvDescriptionDetail.text = it.overview
                            toolbar.title = it.title
                        }
                    }
                    binding.content.visibility = View.VISIBLE
                    binding.appbar.visibility = View.VISIBLE
                    binding.progressBarDetail.visibility = View.GONE
                    DetailTvActivity.showError(binding, View.GONE, it.message)
                }
                ViewState.FAILURE -> {
                    binding.content.visibility = View.INVISIBLE
                    binding.appbar.visibility = View.INVISIBLE
                    binding.progressBarDetail.visibility = View.GONE
                    DetailTvActivity.showError(binding, View.VISIBLE, it.message)
                }
                ViewState.LOADING -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                    binding.content.visibility = View.INVISIBLE
                    binding.appbar.visibility = View.INVISIBLE
                    DetailTvActivity.showError(binding, View.GONE, it.message)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
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
        }
        return true
    }
}