package com.example.appgiphy.ui.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appgiphy.MainRepository
import com.example.appgiphy.R
import com.example.appgiphy.databinding.FragmentDashboardBinding
import com.example.appgiphy.databinding.FragmentHomeBinding
import com.example.appgiphy.recycler.AutoGridLayoutManager
import com.example.appgiphy.recycler.RecyclerAdapter
import com.example.appgiphy.retrofit.RetrofitHelper
import com.example.appgiphy.ui.home.HomeViewModel
import com.example.appgiphy.ui.home.HomeViewModelFactory

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var editTextSearch: EditText

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextSearch = view.findViewById(R.id.editText_dashboard)

        recyclerView = view.findViewById(R.id.recyclerView)
        createRecyclerView()

        dashboardViewModel.gifList.observe(viewLifecycleOwner) {
            recyclerAdapter.setMovieListItems(it)
        }

        if (dashboardViewModel.getRowsGifs() == 0) {
            editTextListenerWatch()
            initScrollListener()
            loadGifs()
        }

    }

    fun createRecyclerView() {
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = activity?.let { AutoGridLayoutManager(it.baseContext, 500) }
        recyclerView.adapter = recyclerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this, DashboardViewModelFactory(MainRepository(RetrofitHelper))).get(
                DashboardViewModel::class.java
            )

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun editTextListenerWatch() {
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    dashboardViewModel.setTextSearch(it.toString())
                    createRecyclerView()
                }
            }

        })
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as GridLayoutManager?
                val lastVisibleItemPosition =
                    linearLayoutManager?.findLastVisibleItemPosition() ?: 0;

                if ((linearLayoutManager != null) && (lastVisibleItemPosition >= (dashboardViewModel.getRowsGifs() - 5))
                ) {
                    //bottom of list!
                    loadGifs()
                }
            }
        })
    }

    fun loadGifs() {
        dashboardViewModel.getNewGifs()
    }



}