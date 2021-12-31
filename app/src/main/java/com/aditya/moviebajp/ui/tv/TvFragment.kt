package com.aditya.moviebajp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.moviebajp.databinding.FragmentTvBinding

class TvFragment : Fragment() {
    private lateinit var binding: FragmentTvBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[TvViewModel::class.java]

        binding.apply {
            val adapter = TvAdapter(viewModel.getData())
            rvTv.layoutManager = LinearLayoutManager(context)
            rvTv.adapter = adapter
        }

        if (viewModel.getData().isEmpty()){
            binding.emptyTv.visibility = View.VISIBLE
            binding.rvTv.visibility = View.GONE
        }else{
            binding.emptyTv.visibility = View.GONE
            binding.rvTv.visibility = View.VISIBLE
        }
    }
}