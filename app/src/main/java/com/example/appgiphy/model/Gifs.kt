package com.example.appgiphy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Gifs(@SerializedName("data") @Expose val datumList: List<Datum>) {
}