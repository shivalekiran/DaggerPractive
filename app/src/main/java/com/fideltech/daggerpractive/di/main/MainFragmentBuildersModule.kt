package com.fideltech.daggerpractive.di.main

import com.fideltech.daggerpractive.ui.main.posts.PostFragment
import com.fideltech.daggerpractive.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contriburePostFragment(): PostFragment
}