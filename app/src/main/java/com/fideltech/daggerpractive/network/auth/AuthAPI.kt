package com.fideltech.daggerpractive.network.auth

import com.fideltech.daggerpractive.ui.auth.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthAPI {

    @GET("/users")
    fun getUsers(): Call<List<User>>
}