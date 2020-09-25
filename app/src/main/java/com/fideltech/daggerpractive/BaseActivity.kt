package com.fideltech.daggerpractive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.fideltech.daggerpractive.models.User
import com.fideltech.daggerpractive.ui.AuthResource
import com.fideltech.daggerpractive.ui.AuthResource.AuthStatus
import com.fideltech.daggerpractive.ui.auth.AuthActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity() : DaggerAppCompatActivity() {

    private val TAG = "DaggerExample"

    @Inject
    public lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun subscribeObservers() {
        sessionManager.getAuthUser().observe(
            this,
            Observer<AuthResource<User?>?>() {
                fun onChanged(userAuthResource: AuthResource<User>?) {
                    if (userAuthResource != null) {
                        when (userAuthResource.status) {
                            AuthStatus.LOADING -> {
                                Log.d(TAG, "onChanged: BaseActivity: LOADING...")
                            }
                            AuthStatus.AUTHENTICATED -> {
                                Log.d(
                                    TAG,
                                    "onChanged: BaseActivity: AUTHENTICATED... " +
                                            "Authenticated as: " + userAuthResource.data!!.email
                                )
                            }
                            AuthStatus.ERROR -> {
                                Log.d(TAG, "onChanged: BaseActivity: ERROR...")
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
                }
            })
    }

    fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}