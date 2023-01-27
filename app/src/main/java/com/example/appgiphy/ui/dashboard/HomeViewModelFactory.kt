package com.example.appgiphy.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appgiphy.MainRepository
import com.example.appgiphy.ui.home.HomeViewModel

class DashboardViewModelFactory constructor(private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return super.create(modelClass)
        return if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            DashboardViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("View model not found")
        }
    }

}