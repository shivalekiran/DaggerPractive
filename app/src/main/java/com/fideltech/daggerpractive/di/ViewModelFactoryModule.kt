package com.fideltech.daggerpractive.di

import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fideltech.daggerpractive.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Provide Factory for view models of activity
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}