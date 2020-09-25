package com.fideltech.daggerpractive.ui.auth

import android.media.session.MediaSessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.SessionManager
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

class AuthViewModel @Inject constructor(val authAPI: AuthAPI, val sessionManager: SessionManager) :
    ViewModel() {


    fun observeAuthUser(): LiveData<AuthResource<User?>> {
        return sessionManager.getAuthUser()
    }

    //getting flowabl dat from api and converting it to Flowable
    //then passing this flowable to @LiveDataReactiveStreams to covert it to LiveData
    fun queryUserId(id: Int): LiveData<AuthResource<User?>> {
        return LiveDataReactiveStreams.fromPublisher(
            authAPI.getUsers(id)
                //if error then returning user with -1 id
                .onErrorReturn { User(-1) }
                //checking here if error then returning error if not then returning  authenticate
                .map(object : Function<User, AuthResource<User?>> {
                    override fun apply(t: User): AuthResource<User?> {
                        if (t.id == -1) {
                            return AuthResource.error("Enter Correct id or check your network.", t)
                        }
                        return AuthResource.authenticated(t)
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun authenticateUser(id: Int) {
        sessionManager.authenticateWithId(queryUserId(id))
    }
}