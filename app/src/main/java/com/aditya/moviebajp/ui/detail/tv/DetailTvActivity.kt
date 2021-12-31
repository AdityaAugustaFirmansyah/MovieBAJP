package com.aditya.moviebajp.ui.detail.tv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.TvEntity
import com.aditya.moviebajp.databinding.ActivityDetailBinding
import com.aditya.moviebajp.ui.tv.TvViewModel
import com.aditya.moviebajp.utils.MovieDummy
import com.bumptech.glide.Glide

class DetailTvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var tvEntity: TvEntity

    companion object {
        const val TAG: String = "tag-detail-tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val viewModel = ViewModelProvider(this)[TvViewModel::class.java]

        viewModel.getData(intent.getIntExtra(TAG,0)).let {
            tvEntity = it
            binding.apply {
                textView7.visibility = View.GONE
                Glide.with(this@DetailTvActivity)
                    .load(MovieDummy.BASE_URL_IMAGE + it.poster_path)
                    .error(R.drawable.ic_broken_image_black).into(posterDetail)

                Glide.with(this@DetailTvActivity)
                    .load(MovieDummy.BASE_URL_IMAGE + it.backdrop_path)
                    .error(R.drawable.ic_broken_image_black).into(backdrop)

                tvTitleDetail.text = it.name
                tvLanguageDetail.text = it.original_language
                tvDateDetail.text = it.first_air_date
                tvPopularityDetail.text = it.popularity.toString()
                tvTotalDetail.text = it.vote_count.toString()
                tvAvarageDetail.text = it.vote_average.toString()
                tvDescriptionDetail.text = it.overview
                toolbar.title = it.name
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
                .setText(getString(R.string.share_text,tvEntity.name))
                .startChooser()
        }
        return true
    }
}