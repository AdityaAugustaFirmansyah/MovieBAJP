package com.aditya.moviebajp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.R
import com.aditya.moviebajp.databinding.FragmentMovieBinding
import com.aditya.moviebajp.viewmodel.ViewModelFactory
import com.aditya.moviebajp.vo.Status

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
            this, ViewModelFactory.getInstance(requireContext())
        )[MovieViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getData().observe(viewLifecycleOwner, {
            if (it.status== Status.LOADING){
                binding.progressBar.visibility = View.VISIBLE
                showError(binding,View.GONE,"")
                binding.recyclerView.visibility = View.GONE
            }else if (it.status == Status.SUCCESS){
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.apply {
                    adapter = it.data?.let { it1 -> MovieAdapter(it1) }
                }
                if (it.message?.isNotEmpty() == true) {
                    binding.emptyMovie.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    showError(binding,View.VISIBLE,getString(R.string.empty_list))
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    showError(binding,View.GONE,"")
                }
            }else if (it.status == Status.FAILURE){
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                it.message?.let { it1 -> showError(binding,View.VISIBLE, it1) }
            }
        })

        binding.toolbar3.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_ascending){
                viewModel.sortingAsc()
            }else if (it.itemId == R.id.menu_descending){
                viewModel.sortingDesc()
            }
            true
        }
    }

    companion object{
        fun showError(binding: FragmentMovieBinding,visibility:Int,message:String){
            binding.emptyMovie.visibility = visibility
            binding.tvError.visibility = visibility
            binding.tvError.text = message
        }
    }
}