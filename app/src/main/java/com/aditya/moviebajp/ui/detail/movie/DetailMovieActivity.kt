package com.aditya.moviebajp.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.databinding.ActivityDetailBinding
import com.aditya.moviebajp.ui.movie.MovieViewModel
import com.aditya.moviebajp.utils.MovieDummy
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

        val viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.getData(intent.getIntExtra(TAG,0)).let {
            movieEntity = it
            binding.apply {
                Glide.with(this@DetailMovieActivity)
                    .load(MovieDummy.BASE_URL_IMAGE + it.poster_path)
                    .error(R.drawable.ic_broken_image_black).into(posterDetail)

                Glide.with(this@DetailMovieActivity)
                    .load(MovieDummy.BASE_URL_IMAGE + it.backdrop_path)
                    .error(R.drawable.ic_broken_image_black).into(backdrop)
                tvTitleDetail.text = it.title
                tvLanguageDetail.text = it.original_language
                tvDateDetail.text = it.release_date
                tvAdultDetail.text = if (it.adult)getString(R.string.yes) else getString(R.string.no)
                tvPopularityDetail.text = it.popularity.toString()
                tvTotalDetail.text = it.vote_count.toString()
                tvAvarageDetail.text = it.vote_average.toString()
                tvDescriptionDetail.text = it.overview
                toolbar.title = it.title
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share){
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder(this)
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_title))
                .setText(getString(R.string.share_text,movieEntity.title))
                .startChooser()
        }
        return true
    }
}