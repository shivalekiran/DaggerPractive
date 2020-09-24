package com.fideltech.daggerpractive.ui.auth

import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.network.auth.AuthAPI
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class AuthViewModel @Inject constructor(val authAPI: AuthAPI) : ViewModel() {
    init {
        val users = authAPI.getUsers()
            .enqueue(object : Callback, retrofit2.Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    println("Error on api call for users")
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    println("List of users ${response.body().toString()}")
                }
            })
        println("Auth view model initiated")
    }
}