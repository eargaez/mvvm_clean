package com.eargaez.mvvm_clean.presentation.main.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eargaez.mvvm_clean.R
import com.eargaez.mvvm_clean.databinding.FragmentBeersBinding
import com.eargaez.mvvm_clean.domain.Resource
import com.eargaez.mvvm_clean.presentation.recycler_view.adapters.BeersRecyclerViewAdapter
import com.eargaez.mvvm_clean.utils.delegate.viewBinding
import com.eargaez.mvvm_clean.utils.handleApiErrors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeersFragment : Fragment(R.layout.fragment_beers) {

    private val viewModel: BeersViewModel by viewModels()

    private val binding by viewBinding(FragmentBeersBinding::bind)

    private lateinit var beersAdapter: BeersRecyclerViewAdapter
    private var pastVisibleItems: Int = 0
    private  var visibleItems: Int = 0
    private var totalItemCount: Int = 0

    private var page: Int = 1

    private var loading = true

    private lateinit var linearManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beersAdapter = BeersRecyclerViewAdapter()

        linearManager = LinearLayoutManager(requireContext())

        binding.postsRvItems.apply {
            setHasFixedSize(true)
            layoutManager = linearManager
            adapter = beersAdapter
        }

        runCollectors()

        runObservers()

        binding.postsRvItems.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItems = linearManager.itemCount
                    totalItemCount = linearManager.itemCount
                    pastVisibleItems = linearManager.findLastCompletelyVisibleItemPosition()

                    if (loading) {
                       if (pastVisibleItems >= visibleItems - 1) {
                           loading = false
                           page++
                           viewModel.getBeers()
                           loading = true
                       }
                    }
                }
            }
        })
    }

    private fun runCollectors() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.result.collect {
            val result = it ?: return@collect

            when (result) {
                is Resource.Success -> {
                    viewModel.addItems(it)
                }
                is Resource.Failure -> handleApiErrors(result) { viewModel.getBeers() }
            }
        }

    }

    private fun runObservers() {
        viewModel.beers.observe(viewLifecycleOwner) {
            val starIndex = if(beersAdapter.items.isEmpty()) 0 else beersAdapter.items.size -1
            val endIndex = it.size

            beersAdapter.items.addAll(it.subList(starIndex, endIndex))
            beersAdapter.notifyDataSetChanged()
        }
    }

}