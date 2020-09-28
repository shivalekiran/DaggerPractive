package com.fideltech.daggerpractive.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.SessionManager
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.ui.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val sessionManager: SessionManager) : ViewModel() {
    init {
        println("Profile model is ready")
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User?>> {
        return sessionManager.getAuthUser()
    }
}