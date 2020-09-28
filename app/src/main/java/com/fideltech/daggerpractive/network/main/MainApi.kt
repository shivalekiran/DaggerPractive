package com.fideltech.daggerpractive.network.main

import com.fideltech.daggerpractive.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    //post api
    @GET("posts")
    fun getPostForUser(@Query("userId") id: Int): Flowable<List<Post>>
}