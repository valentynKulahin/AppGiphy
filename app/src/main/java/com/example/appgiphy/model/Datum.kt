package com.example.appgiphy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("type") @Expose val type: String, @SerializedName("id") @Expose val id: String,
    @SerializedName("url") @Expose val url: String, @SerializedName("slug") @Expose val slug: String,
    @SerializedName("title") @Expose val title: String, @SerializedName("rating") @Expose val rating: String,
    @SerializedName("is_sticker") @Expose val is_sticker: Int,
    @SerializedName("importDatetime") @Expose val importDatetime: String,
    @SerializedName("trendingDateTime") @Expose val trendingDateTime: String,
    @SerializedName("images") @Expose val images: Images
) {
}