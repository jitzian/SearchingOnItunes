package org.com.testing.with.musicartistsample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.com.testing.with.networkutil.NetworkMonitorUtil
import kotlin.properties.Delegates

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var TAG: String
    protected var networkMonitor: NetworkMonitorUtil by Delegates.notNull()
    protected var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            networkMonitor = NetworkMonitorUtil(this)
        }
    }

    override fun onStart() {
        super.onStart().also {
            networkMonitor.register()
        }
    }

    override fun onStop() {
        super.onStop().also {
            networkMonitor.unregister()
        }
    }

    abstract fun initView()
    abstract fun setupObservers()

}