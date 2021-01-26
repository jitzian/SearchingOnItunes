package org.com.testing.with.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import org.com.testing.with.musicartistsample.base.BaseActivity
import org.com.testing.with.musicartistsample.base.factories.ViewModelFactory
import org.com.testing.with.musicartistsample.databinding.ActivityMainBinding
import org.com.testing.with.networkutil.ConnectionType
import org.com.testing.with.ui.viewmodels.MainViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    init {
        TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            initView()
            initViewModel()
        }
    }

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNetworkListener()
    }

    private fun initViewModel() {
        mainVM = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
    }

    override fun setupObservers() {
        //TODO("Not yet implemented")
        mainVM.fetchRemoteData()
    }

    private fun initNetworkListener() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.i("NETWORK_MONITOR_STATUS", "Wifi Connection")
                                isConnected = true
//                                showVM.fetchDataAndStoreIntoDb().also {
//                                    setupObservers()
//                                }
                                setupObservers()
                            }
                            ConnectionType.Cellular -> {
                                Log.i("NETWORK_MONITOR_STATUS", "Cellular Connection")
                                isConnected = true
//                                setupObservers()
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        Log.e("NETWORK_MONITOR_STATUS", "No Connection")
                        isConnected = false
                    }
                }
            }
        }
    }

}