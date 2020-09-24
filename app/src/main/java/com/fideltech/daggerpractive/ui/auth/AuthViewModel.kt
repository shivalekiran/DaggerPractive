package com.fideltech.daggerpractive.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.network.auth.AuthAPI
import com.fideltech.daggerpractive.ui.AuthResource
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import org.reactivestreams.Subscriber
import java.util.*
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authAPI: AuthAPI) : ViewModel() {

    val authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }

    fun authenticateUser(id: Int) {
        @Suppress("UNCHECKED_CAST")
        authUser.value = AuthResource.loading(data = null) as AuthResource<User>
        val source = LiveDataReactiveStreams.fromPublisher(
            authAPI.getUsers(id)
                .onErrorReturn (object : Function<Throwable, User> {
                    override fun apply(t: Throwable): User {
                        return User()
                    }
                })
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(source, androidx.lifecycle.Observer {
            authUser.value = it
            authUser.removeSource(source)
        })
    }

    init {
        val users = authAPI.getUsers(1)
            .toObservable()
            .subscribeOn(io())
            .subscribe({ user ->
                println("User details - $user")
            })
    }
}