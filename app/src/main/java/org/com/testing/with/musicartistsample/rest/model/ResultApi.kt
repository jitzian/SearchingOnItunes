package org.com.testing.with.musicartistsample.rest.model


import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("resultCount")
    val resultCount: Int?,
    @SerializedName("results")
    val results: List<Result>?
)