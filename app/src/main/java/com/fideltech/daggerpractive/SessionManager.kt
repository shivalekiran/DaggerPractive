package com.fideltech.daggerpractive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.ui.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cashedUser = MediatorLiveData<AuthResource<User?>>()

    fun authenticateWithId(source: LiveData<AuthResource<User?>>) {
        cashedUser.let {
            it.value = AuthResource.loading(null) as AuthResource<User?>
            cashedUser.addSource(source) { autUser ->
                cashedUser.value = autUser
                cashedUser.removeSource(source)
            }
        }
    }

    fun logOut() {
        println("Loging out user")
        cashedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User?>> {
        return cashedUser
    }
}