package com.aditya.moviebajp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.databinding.FragmentMovieBinding
import com.aditya.moviebajp.ui.movie.MovieFragment
import com.aditya.moviebajp.utils.SortUtils
import com.aditya.moviebajp.viewmodel.ViewModelFactory
import com.aditya.moviebajp.vo.Resource
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
                viewModel.getData(SortUtils.ASCENDING).observe(viewLifecycleOwner,observer)
            }else if (it.itemId == R.id.menu_descending){
                viewModel.getData(SortUtils.DESCENDING).observe(viewLifecycleOwner,observer)
            }
            true
        }

        viewModel.getData(SortUtils.DEFAULT).observe(viewLifecycleOwner,observer)
    }
    private val observer = Observer<Resource<PagedList<TvEntity>>>{
        when (it.status) {
            Status.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                MovieFragment.showError(binding, View.GONE, getString(R.string.empty_list))
                binding.recyclerView.visibility = View.GONE
            }
            Status.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                val adapter = TvAdapter()
                binding.apply {
                    it.data?.let { it1 -> adapter.submitList(it1) }
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
    }
}