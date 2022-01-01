package com.aditya.moviebajp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.databinding.FragmentMovieBinding
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.ui.movie.MovieFragment
import com.aditya.moviebajp.viewmodel.ViewModelFactory

class TvFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(ApiClient.restApi())
        )[TvViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getData().observe(viewLifecycleOwner, {
            when (it.viewState) {
                ViewState.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    MovieFragment.showError(binding, View.GONE, getString(R.string.empty_list))
                    binding.recyclerView.visibility = View.GONE
                }
                ViewState.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.apply {
                        val adapter = TvAdapter(it.tvs)
                        recyclerView.adapter = adapter

                        if (it.tvs.isEmpty()) {
                            recyclerView.visibility = View.GONE
                            MovieFragment.showError(binding, View.VISIBLE, getString(R.string.empty_list))
                        } else {
                            recyclerView.visibility = View.VISIBLE
                            MovieFragment.showError(binding, View.GONE, getString(R.string.empty_list))
                        }
                    }
                }
                ViewState.FAILURE -> {
                    binding.progressBar.visibility = View.GONE
                    MovieFragment.showError(binding, View.VISIBLE, it.msg)
                }
            }
        })
    }
}