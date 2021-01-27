package org.com.testing.with.showArtist.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.com.testing.with.musicartistsample.base.BaseActivity
import org.com.testing.with.musicartistsample.base.factories.ViewModelFactory
import org.com.testing.with.musicartistsample.databinding.ActivityMainBinding
import org.com.testing.with.networkutil.ConnectionType
import org.com.testing.with.showArtist.ui.adapters.RVCustomAdapter
import org.com.testing.with.showArtist.ui.viewmodels.MainViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    //RecyclerView variables
    private lateinit var adapter: RVCustomAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    init {
        TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            initView()
            initViewModel()
        }
    }

    override fun onStart() {
        super.onStart().also {
            mainVM.fetchData("Linkin Park")
        }
    }

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRV()
        initNetworkListener()
    }

    private fun initViewModel() {
        mainVM = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
    }

    override fun setupObservers() {
        //TODO("Not yet implemented")
        mainVM.artistResult.observe(this, Observer { listOfArtistAlbums ->
            Log.e(TAG, "setupObservers::${listOfArtistAlbums}")
            adapter = RVCustomAdapter()
            adapter.setData(listOfArtistAlbums)
            binding.mRecyclerView.adapter = adapter
        })
    }

    private fun setupRV() {
        layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView.layoutManager = layoutManager
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