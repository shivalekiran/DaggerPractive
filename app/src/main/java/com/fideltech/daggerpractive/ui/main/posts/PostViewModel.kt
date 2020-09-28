package com.fideltech.daggerpractive.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.fideltech.daggerpractive.SessionManager
import com.fideltech.daggerpractive.models.Post
import com.fideltech.daggerpractive.network.main.MainApi
import com.fideltech.daggerpractive.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class PostViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var mainApi: MainApi

    var posts: MediatorLiveData<Resource<List<Post>>>? = null

    fun observerPosts(): LiveData<Resource<List<Post>>>? {
        if (posts == null) {
            posts = MediatorLiveData<Resource<List<Post>>>()
            posts!!.value = Resource.loading(null)
            val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostForUser(sessionManager.getAuthUser().value?.data!!.id)
                    .onErrorReturn(Function<Throwable, List<Post>> {
                        return@Function emptyList()
                    })
                    //mapping form API response to Resource<>
                    //input-> value form API
                    //output-> value you want to convert
                    .map(object : Function<List<Post>, Resource<List<Post>>> {
                        override fun apply(list: List<Post>): Resource<List<Post>> {
                            println("Mapping - ${list.toString()}")
                            if (list.isEmpty())  {
                                return Resource.error("", null)
                            }
                            return Resource.success(list)
                        }
                    })
                    .subscribeOn(io())
            )
            posts!!.addSource(source, Observer {
                posts!!.value = it
                posts!!.removeSource(source)
            })
        }
        return posts
    }
}