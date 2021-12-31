package com.aditya.moviebajp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.moviebajp.R
import com.aditya.moviebajp.databinding.ActivityHomeBinding
import com.aditya.moviebajp.ui.movie.MovieFragment
import com.aditya.moviebajp.ui.tv.TvFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragments = mutableListOf(MovieFragment(),TvFragment())
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.movie)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.tv)))
        binding.viewPager.adapter = HomePagerAdapter(this,fragments)

        TabLayoutMediator(binding.tabLayout,binding.viewPager)
        { tab, position -> tab.text = if (position==0)getString(R.string.movie) else getString(R.string.tv) }.attach()
    }
}