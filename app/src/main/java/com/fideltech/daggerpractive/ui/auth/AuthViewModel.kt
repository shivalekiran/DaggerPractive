package com.fideltech.daggerpractive.ui.auth

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {
    init {
        println("Auth view model initiated")
    }
}