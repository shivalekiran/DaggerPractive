package com.fideltech.daggerpractive.di.main

import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.di.ViewModelKey
import com.fideltech.daggerpractive.models.Post
import com.fideltech.daggerpractive.ui.main.posts.PostViewModel
import com.fideltech.daggerpractive.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(profileViewModel: PostViewModel): ViewModel

}