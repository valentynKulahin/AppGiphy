package com.example.appgiphy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(@SerializedName("original") @Expose val original: Original) {
}