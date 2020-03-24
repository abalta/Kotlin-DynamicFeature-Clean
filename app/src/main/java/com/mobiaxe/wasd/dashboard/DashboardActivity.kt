package com.mobiaxe.wasd.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.mobiaxe.wasd.R
import com.mobiaxe.wasd.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private var binding: ActivityDashboardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding?.let {
            Navigation.findNavController(this, R.id.nav_host_fragment).apply {
                it.bottomNav.setupWithNavController(this)
            }
        }
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

}