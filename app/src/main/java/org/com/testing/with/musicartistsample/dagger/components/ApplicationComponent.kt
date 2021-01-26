package org.com.testing.with.musicartistsample.dagger.components

import dagger.Component
import org.com.testing.with.musicartistsample.CustomApp
import org.com.testing.with.musicartistsample.dagger.modules.ApplicationModule

@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(customApp: CustomApp)

    @Component.Builder
    interface Builder {
        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun build(): ApplicationComponent
    }

}