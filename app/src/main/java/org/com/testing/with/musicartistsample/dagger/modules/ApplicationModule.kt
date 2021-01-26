package org.com.testing.with.musicartistsample.dagger.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.com.testing.with.musicartistsample.CustomApp

@Module
class ApplicationModule(private val app: CustomApp) {

    @Provides
    @Reusable
    fun providesApp() = app

}