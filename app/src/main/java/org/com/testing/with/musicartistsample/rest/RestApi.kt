package org.com.testing.with.musicartistsample.rest

import org.com.testing.with.musicartistsample.rest.model.ResultApi
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RestApi {

    @GET("search/")
    suspend fun fetchRemoteData(@QueryMap queryMap: Map<String, String>): ResultApi
}