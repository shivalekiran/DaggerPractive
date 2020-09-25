package com.fideltech.daggerpractive.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.fideltech.daggerpractive.R
import com.fideltech.daggerpractive.ui.AuthResource
import com.fideltech.daggerpractive.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var applicationName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        login_button.setOnClickListener {
            loginUser()
        }
        subscribeUserObserve()
        setLogo()
    }

    private fun subscribeUserObserve() {

        viewModel.observeAuthUser().observe(this, Observer { authUser ->
            progress_bar.visibility = View.GONE
            authUser?.let {
                when (authUser.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        println("User is Authenticated")
                    }
                    AuthResource.AuthStatus.ERROR -> Toast.makeText(
                        application,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    AuthResource.AuthStatus.LOADING -> progress_bar.visibility = View.VISIBLE
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> TODO()
                }
            }
        })
    }

    private fun loginUser() {
        if (!TextUtils.isEmpty(user_id_input.text.toString().trim())) {
            viewModel.authenticateUser(user_id_input.text.toString().toInt())
        }
    }

    fun setLogo() {
        requestManager.load(logo)
            .into(login_logo)
    }
}
