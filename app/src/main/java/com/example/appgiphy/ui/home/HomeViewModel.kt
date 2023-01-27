package com.example.appgiphy.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appgiphy.MainRepository
import com.example.appgiphy.model.Gifs
import com.example.appgiphy.retrofit.ApiInterface
import com.example.appgiphy.ui.CommandInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: MainRepository) : ViewModel(), CommandInterface {

    val gifList = MutableLiveData<Gifs>()
    val errorMessage = MutableLiveData<String>()
    private var rowsGifs: Int = 0

    override fun getNewGifs() {
        val data = repository.getRetrofitInstance().create(ApiInterface::class.java)
        val result: Call<Gifs> = data.getGifs("KQnnQMPANah30mwZ0VBXGKXXFz36uppm", 20, rowsGifs,  "g")
        result.enqueue(object : Callback<Gifs> {
            override fun onResponse(call: Call<Gifs>, response: Response<Gifs>) {
                Log.d(ContentValues.TAG, "onResponse: $result")
                gifList.postValue(response.body())
                rowsGifs += 20
            }

            override fun onFailure(call: Call<Gifs>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: $result")
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getRowsGifs(): Int {
        return rowsGifs
    }

}