package com.fideltech.daggerpractive.di.auth

import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.network.auth.AuthAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AuthModule {
    @Provides
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }
    //same type object return
    //cant inject same type from other module
    //delete after screen rotate bcz auth scope ie sub component delete so it also delete
//    @Provides
//    @Named("User_Auth")
//    fun getUser(): User {
//        return User(-1)
//    }

}