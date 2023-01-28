package com.akinguldere.etsturcase.ui.splash

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.akinguldere.etsturcase.EtsActivity
import com.akinguldere.etsturcase.R
import com.akinguldere.etsturcase.databinding.ActivitySplashBinding
import com.akinguldere.etsturcase.ui.main.MainActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : EtsActivity() {

    private val binding by binding<ActivitySplashBinding>(R.layout.activity_splash)

    private var splashModel = SplashModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@SplashActivity
            splashModel = this@SplashActivity.splashModel
        }

        // Check internet connection
        if (checkForInternet(this)) {
            splashModel.isConnected = true
            getFirebaseConfig()

            lifecycleScope.launch {
                // Wait for 3 seconds
                delay(3000)
                goToMain()
            }

        } else {
            splashModel.isConnected = false
            splashModel.textForScreen = getString(R.string.no_internet_connection)
        }

    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }

    private fun getFirebaseConfig() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val appName = remoteConfig.getString("appName")
                    splashModel.textForScreen = appName
                } else {
                    splashModel.textForScreen = "Firebase Config Error"
                }
            }
    }

}