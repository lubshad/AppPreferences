package com.example.appsettings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() {

    private val sharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, s ->
            val nightmode =
                sharedPreferences.getString(SettingsActivity.SettingsFragment.NIGHT_MODE_KEY, "1")
            Log.e(SettingsActivity.SettingsFragment.TAG, nightmode.toString())
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getPreferences(MODE_PRIVATE)

        preferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> {
                navigateToSettingsActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}