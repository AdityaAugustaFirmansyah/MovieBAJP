package com.aditya.moviebajp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.R
import com.aditya.moviebajp.databinding.FragmentMovieBinding
import com.aditya.moviebajp.ui.movie.MovieFragment
import com.aditya.moviebajp.viewmodel.ViewModelFactory
import com.aditya.moviebajp.vo.Status

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
            ViewModelFactory.getInstance(requireContext())
        )[TvViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.toolbar3.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_ascending){
                viewModel.sortingAsc()
            }else if (it.itemId == R.id.menu_descending){
                viewModel.sortingDesc()
            }
            true
        }

        viewModel.getData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    MovieFragment.showError(binding, View.GONE, getString(R.string.empty_list))
                    binding.recyclerView.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.apply {
                        val adapter = it.data?.let { it1 -> TvAdapter(it1) }
                        recyclerView.adapter = adapter

                        if (it.message?.isEmpty() == true) {
                            recyclerView.visibility = View.GONE
                            MovieFragment.showError(binding, View.VISIBLE, getString(R.string.empty_list))
                        } else {
                            recyclerView.visibility = View.VISIBLE
                            MovieFragment.showError(binding, View.GONE, getString(R.string.empty_list))
                        }
                    }
                }
                Status.FAILURE -> {
                    binding.progressBar.visibility = View.GONE
                    it.message?.let { it1 -> MovieFragment.showError(binding, View.VISIBLE, it1) }
                }
            }
        })
    }
}