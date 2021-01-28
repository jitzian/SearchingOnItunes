package org.com.testing.with.showArtist.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.com.testing.with.musicartistsample.R
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

    override fun onResume() {
        super.onResume().also {
            mainVM.fetchData(binding.mSearchViewSearchByArtistName.query.toString())
        }
    }

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRV()
        initNetworkListener()
        setupSearchView()
    }

    private fun initViewModel() {
        mainVM = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
    }

    override fun setupObservers() {
        mainVM.artistResult.observe(this, Observer { listOfArtistAlbums ->
            adapter = RVCustomAdapter()
            adapter.apply {
                setData(listOfArtistAlbums)
                binding.mRecyclerView.adapter = this
            }
            binding.mRecyclerView.adapter = adapter
        })
    }

    private fun setupRV() {
        layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView.layoutManager = layoutManager
    }

    private fun setupSearchView() {
        binding.mSearchViewSearchByArtistName.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mainVM.fetchData(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initNetworkListener() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.i(
                                    TAG,
                                    "initNetworkListener::${getString(R.string.network_status)}::${
                                        resources.getString(R.string.string_wifi_connection)
                                    }"
                                )
                                isConnected = true
                                binding.mSearchViewSearchByArtistName.visibility = View.VISIBLE
                                setupObservers()
                            }
                            ConnectionType.Cellular -> {
                                Log.i(
                                    TAG,
                                    "initNetworkListener::${getString(R.string.network_status)}::${
                                        resources.getString(R.string.string_cellular_connection)
                                    }"
                                )
                                isConnected = true
                                binding.mSearchViewSearchByArtistName.visibility = View.VISIBLE
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        Log.e(
                            "$TAG::${getString(R.string.network_status)}",
                            getString(R.string.string_no_connectivity)
                        )
                        isConnected = false
                        Toast.makeText(
                            this,
                            getString(R.string.string_there_is_no_connectivity),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.mSearchViewSearchByArtistName.visibility = View.GONE
                    }
                }
            }
        }
    }

}