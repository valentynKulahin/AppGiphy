package com.example.appgiphy.ui.home

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appgiphy.MainRepository
import com.example.appgiphy.R
import com.example.appgiphy.databinding.FragmentHomeBinding
import com.example.appgiphy.recycler.AutoGridLayoutManager
import com.example.appgiphy.recycler.RecyclerAdapter
import com.example.appgiphy.retrofit.RetrofitHelper

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerAdapter = RecyclerAdapter(this)
        //GRIDLAYOUT IN XML
        recyclerView.layoutManager = activity?.let { AutoGridLayoutManager(it.baseContext, 500) }
        recyclerView.adapter = recyclerAdapter

        homeViewModel.gifList.observe(viewLifecycleOwner) {
            recyclerAdapter.setMovieListItems(it)
        }

        if (homeViewModel.getRowsGifs() == 0) {
            initScrollListener()
            loadGifs()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(MainRepository(RetrofitHelper))).get(
                HomeViewModel::class.java
            )

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as GridLayoutManager?
                val lastVisibleItemPosition =
                    linearLayoutManager?.findLastVisibleItemPosition() ?: 0;

                if ((linearLayoutManager != null) && (lastVisibleItemPosition >= (homeViewModel.getRowsGifs() - 5))
                ) {
                    //bottom of list!
                    loadGifs()
                }
            }
        })
    }

    fun loadGifs() {
        homeViewModel.getNewGifs()
    }

}