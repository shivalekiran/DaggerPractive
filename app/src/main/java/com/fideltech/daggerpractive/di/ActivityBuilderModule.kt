package com.fideltech.daggerpractive.di

import com.fideltech.daggerpractive.di.auth.AuthModule
import com.fideltech.daggerpractive.di.auth.AuthViewModelModule
import com.fideltech.daggerpractive.ui.auth.AuthActivity
import com.fideltech.daggerpractive.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun injectAuthActivity(): AuthActivity

    @ContributesAndroidInjector()
    abstract fun injectMainActivity(): MainActivity
}