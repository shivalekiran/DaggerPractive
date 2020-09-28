package com.fideltech.daggerpractive.di.main

import com.fideltech.daggerpractive.network.main.MainApi
import com.fideltech.daggerpractive.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    fun getMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    fun provideRecycleAdapter(): PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }
}