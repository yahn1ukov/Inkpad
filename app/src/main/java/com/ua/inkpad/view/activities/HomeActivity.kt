package com.ua.inkpad.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ua.inkpad.R
import com.ua.inkpad.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.homeScreenToolbar)

        val clickHandler = HomeActivityClickHandler(this)
        binding.clickHandler = clickHandler

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    class HomeActivityClickHandler(private val context: Context) {
        fun onFABAddClick(view: View) {
            context.startActivity(Intent(context, AddNoteActivity::class.java))
        }

        fun onFABSettingsClick(view: View) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }
}
