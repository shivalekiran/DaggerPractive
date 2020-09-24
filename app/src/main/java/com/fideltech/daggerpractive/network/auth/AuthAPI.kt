package com.fideltech.daggerpractive.network.auth

import com.fideltech.daggerpractive.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthAPI {

    @GET("/users/{id}")
    fun getUsers(@Path("id") id: Int): Flowable<User>
}