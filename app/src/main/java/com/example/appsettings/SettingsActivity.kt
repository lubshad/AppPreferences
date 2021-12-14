package com.example.appsettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import com.google.android.material.tabs.TabLayout


class SettingsActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        companion object {
            const val TAG ="SettingsFragment"
            const val NIGHT_MODE_KEY = "night_mode_preference"
        }

        private val sharedPreferenceChangeListener = OnSharedPreferenceChangeListener { sharedPreferences, s ->
            val nightmode = sharedPreferences.getString(NIGHT_MODE_KEY, "1")
            when (nightmode) {
                "1" ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                "2" ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                "3" ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val preferences = preferenceManager.sharedPreferences
            preferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)
        }
    }
}