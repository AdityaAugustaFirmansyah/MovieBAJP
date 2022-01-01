package com.aditya.moviebajp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.R
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.databinding.FragmentMovieBinding
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.viewmodel.ViewModelFactory

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
            this, ViewModelFactory.getInstance(ApiClient.restApi())
        )[MovieViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getData().observe(viewLifecycleOwner, {
            if (it.viewState==ViewState.LOADING){
                binding.progressBar.visibility = View.VISIBLE
                showError(binding,View.GONE,"")
                binding.recyclerView.visibility = View.GONE
            }else if (it.viewState == ViewState.SUCCESS){
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.apply {
                    adapter = MovieAdapter(it.success)
                }
                if (it.success.isEmpty()) {
                    binding.emptyMovie.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    showError(binding,View.VISIBLE,getString(R.string.empty_list))
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    showError(binding,View.GONE,"")
                }
            }else if (it.viewState == ViewState.FAILURE){
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                showError(binding,View.VISIBLE,it.msg)
            }
        })
    }
    companion object{
        fun showError(binding: FragmentMovieBinding,visibility:Int,message:String){
            binding.emptyMovie.visibility = visibility
            binding.tvError.visibility = visibility
            binding.tvError.text = message
        }
    }
}