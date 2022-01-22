package com.aditya.moviebajp.ui.favourite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.R
import com.aditya.moviebajp.databinding.FragmentMovieBinding
import com.aditya.moviebajp.ui.movie.MovieAdapter
import com.aditya.moviebajp.viewmodel.ViewModelFactory

class MovieFavouriteFragment : Fragment() {

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
            this, ViewModelFactory.getInstance(requireContext())
        )[MovieFavouriteViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.toolbar3.visibility = View.GONE
        val adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.getData().observe(viewLifecycleOwner, {
            adapter.submitList(it)
            if (it.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                showError(binding,View.GONE,"")
            } else{
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                showError(binding, View.VISIBLE, getString(R.string.empty_list))
            }
        })
    }

    companion object {
        fun showError(binding: FragmentMovieBinding, visibility: Int, message: String) {
            binding.emptyMovie.visibility = visibility
            binding.tvError.visibility = visibility
            binding.tvError.text = message
        }
    }
}