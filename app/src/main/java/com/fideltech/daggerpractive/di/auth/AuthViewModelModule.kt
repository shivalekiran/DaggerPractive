package com.fideltech.daggerpractive.di.auth

import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.di.ViewModelKey
import com.fideltech.daggerpractive.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}