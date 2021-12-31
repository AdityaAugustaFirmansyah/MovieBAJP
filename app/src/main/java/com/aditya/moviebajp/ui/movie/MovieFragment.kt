package com.aditya.moviebajp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

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
            this
        )[MovieViewModel::class.java]

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(
                viewModel.getData()
            )
        }

        if (viewModel.getData().isEmpty()){
            binding.emptyMovie.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }else{
            binding.emptyMovie.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}