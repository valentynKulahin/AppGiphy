package com.example.appgiphy

import com.example.appgiphy.retrofit.RetrofitHelper

class MainRepository constructor(val retrofitHelper: RetrofitHelper) {

    fun getRetrofitInstance() = RetrofitHelper.getInstance()

}