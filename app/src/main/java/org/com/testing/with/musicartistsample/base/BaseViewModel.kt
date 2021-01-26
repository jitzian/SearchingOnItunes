package org.com.testing.with.musicartistsample.base

import androidx.lifecycle.ViewModel
import org.com.testing.with.musicartistsample.constants.GlobalConstants
import org.com.testing.with.musicartistsample.dagger.components.DaggerNetworkComponentInjector
import org.com.testing.with.musicartistsample.dagger.modules.NetworkModule
import org.com.testing.with.musicartistsample.rest.RestApi
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    protected lateinit var TAG: String
    protected val injector = DaggerNetworkComponentInjector.builder()
        .networkModule(NetworkModule(GlobalConstants.baseUrl))
        .build()

    protected lateinit var restApi: RestApi

    @Inject
    lateinit var retrofit: Retrofit

    init {
        inject()
    }

    private fun inject(){
        injector.inject(this)
        restApi = retrofit.create(RestApi::class.java)
    }

}