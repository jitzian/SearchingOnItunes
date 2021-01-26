package org.com.testing.with.musicartistsample.dagger.components

import dagger.Component
import org.com.testing.with.musicartistsample.base.BaseViewModel
import org.com.testing.with.musicartistsample.dagger.modules.NetworkModule

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface NetworkComponentInjector {

    fun inject(baseViewModel: BaseViewModel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): NetworkComponentInjector
    }

}