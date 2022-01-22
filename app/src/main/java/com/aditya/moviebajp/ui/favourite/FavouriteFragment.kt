package com.aditya.moviebajp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aditya.moviebajp.R
import com.aditya.moviebajp.databinding.FragmentFavouriteBinding
import com.aditya.moviebajp.ui.favourite.movie.MovieFavouriteFragment
import com.aditya.moviebajp.ui.favourite.tv.TvFavouriteFragment
import com.google.android.material.tabs.TabLayoutMediator

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tabLayoutFavourite.addTab(
            binding.tabLayoutFavourite.newTab().setText(getString(R.string.movie))
        )

        binding.tabLayoutFavourite.addTab(
            binding.tabLayoutFavourite.newTab().setText(getString(R.string.tv))
        )

        binding.viewPagerFavourite.adapter = FavouritePagerAdapter(this,
            mutableListOf(MovieFavouriteFragment(),TvFavouriteFragment()))

        TabLayoutMediator(
            binding.tabLayoutFavourite, binding.viewPagerFavourite
        ) { tab, position ->
            tab.text = if (position == 0) getString(R.string.movie) else getString(R.string.tv)
        }.attach()
    }
}