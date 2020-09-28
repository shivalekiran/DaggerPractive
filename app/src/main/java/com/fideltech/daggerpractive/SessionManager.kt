package com.fideltech.daggerpractive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.ui.AuthResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser = MediatorLiveData<AuthResource<User?>>()

    fun authenticateWithId(source: LiveData<AuthResource<User?>>) {
        cachedUser.let {
            it.value = AuthResource.loading(null) as AuthResource<User?>
            cachedUser.addSource(source) { autUser ->

                cachedUser.value = autUser
                cachedUser.removeSource(source)
                if (autUser.status.equals(AuthResource.AuthStatus.ERROR)) {
                    cachedUser.setValue(AuthResource.logout())
                }
            }
        }
    }

    fun logOut() {
        println("Loging out user")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User?>> {
        return cachedUser
    }
}