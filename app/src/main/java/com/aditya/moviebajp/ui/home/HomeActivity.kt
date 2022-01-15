package com.aditya.moviebajp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aditya.moviebajp.R
import com.aditya.moviebajp.databinding.ActivityHomeBinding
import com.aditya.moviebajp.ui.favourite.FavouriteFragment
import com.aditya.moviebajp.ui.movie.MovieFragment
import com.aditya.moviebajp.ui.tv.TvFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(MovieFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_movie->{
                    loadFragment(MovieFragment())
                }

                R.id.menu_tv->{
                    loadFragment(TvFragment())
                }

                R.id.menu_favourite->{
                    loadFragment(FavouriteFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id,fragment)
            .commit()
    }
}