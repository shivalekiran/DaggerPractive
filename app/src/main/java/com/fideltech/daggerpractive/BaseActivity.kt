package com.fideltech.daggerpractive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.ui.AuthResource
import com.fideltech.daggerpractive.ui.AuthResource.AuthStatus
import com.fideltech.daggerpractive.ui.auth.AuthActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    fun subscribeObservers() {
        sessionManager.getAuthUser().observe(
            this,
            Observer<AuthResource<User?>> { userAuthResource ->
                if (userAuthResource != null) {
                    when (userAuthResource.status) {
                        AuthStatus.LOADING -> {
                            Log.d(
                                TAG,
                                "onChanged: BaseActivity: LOADING..."
                            )
                        }
                        AuthStatus.AUTHENTICATED -> {
                            Log.d(
                                TAG,
                                "onChanged: BaseActivity: AUTHENTICATED... " +
                                        "Authenticated as: " + userAuthResource.data!!.email
                            )
                        }
                        AuthStatus.ERROR -> {
                            Log.d(
                                TAG,
                                "onChanged: BaseActivity: ERROR..."
                            )
                        }
                        AuthStatus.NOT_AUTHENTICATED -> {
                            Log.d(
                                TAG,
                                "onChanged: BaseActivity: NOT AUTHENTICATED. Navigating to Login screen."
                            )
                            navLoginScreen()
                        }
                    }
                }
            })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "DaggerExample"
    }
}