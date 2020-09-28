package com.fideltech.daggerpractive.ui.main.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.fideltech.daggerpractive.R
import com.fideltech.daggerpractive.ui.main.Resource
import com.fideltech.daggerpractive.util.VerticalSpacingItemDecoration
import com.fideltech.daggerpractive.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class PostFragment : DaggerFragment() {

    lateinit var viewModel: PostViewModel

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)
        initRecycleView()
        subcribeObserver()
    }

    fun subcribeObserver() {
        viewModel.observerPosts()?.removeObservers(viewLifecycleOwner)
        viewModel.observerPosts()?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS ->
                    it.data?.let { it1 -> adapter.setPosts(it1) }
                Resource.Status.ERROR ->
                    println("Some Error..." + it.message)

                Resource.Status.LOADING -> println("Loading...")
            }
        })
    }

    fun initRecycleView() {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpacingItemDecoration(15)
        recycler_view.addItemDecoration(itemDecoration)
        recycler_view.adapter = adapter
    }
}
