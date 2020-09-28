package com.fideltech.daggerpractive.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.fideltech.daggerpractive.BaseActivity
import com.fideltech.daggerpractive.R
import com.fideltech.daggerpractive.ui.main.posts.PostFragment
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PostFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.logout_menu -> sessionManager.logOut()
        }
        return true
    }
}
