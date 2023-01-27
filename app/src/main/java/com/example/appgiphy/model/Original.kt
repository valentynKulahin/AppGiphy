package com.example.appgiphy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("height") @Expose val height: String, @SerializedName("width") @Expose val width: String,
    @SerializedName("size") @Expose val size: String, @SerializedName("url") @Expose val url: String,
    @SerializedName("mp4") @Expose val mp4: String, @SerializedName("webp") @Expose val webp: String,
    @SerializedName("frames") @Expose val frames: String, @SerializedName("hash") @Expose val hash: String
) {
}