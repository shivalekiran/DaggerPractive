package com.fideltech.daggerpractive.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.fideltech.daggerpractive.R
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.ui.AuthResource
import com.fideltech.daggerpractive.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var profileViewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileViewModel =
            ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        subscribeObserver()
    }

    fun subscribeObserver() {
        //removing observer previous
        profileViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        //setting observer
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer { authUser ->
            authUser?.let {
                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        it.data?.let {
                            setUserDetails(it)
                        }
                    }
                    AuthResource.AuthStatus.ERROR -> setErrorDetails(it.message)
                    else -> println("")
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = message
        website.text = message
    }

    private fun setUserDetails(user: User) {
        email.text = user.email
        username.text = user.username
        website.text = user.address.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}
