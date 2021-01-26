package org.com.testing.with.musicartistsample

import android.os.Bundle
import android.util.Log
import org.com.testing.with.musicartistsample.base.BaseActivity
import org.com.testing.with.musicartistsample.databinding.ActivityMainBinding
import org.com.testing.with.networkutil.ConnectionType

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    init {
        TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            initView()
        }
    }

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNetworkListener()
    }


    override fun setupObservers() {
        //TODO("Not yet implemented")
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