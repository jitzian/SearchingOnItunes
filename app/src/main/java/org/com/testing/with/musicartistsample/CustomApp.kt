package org.com.testing.with.musicartistsample

import android.app.Application
import org.com.testing.with.musicartistsample.base.database.ArtistAlbumsDb
import org.com.testing.with.musicartistsample.dagger.components.DaggerApplicationComponent
import org.com.testing.with.musicartistsample.dagger.modules.ApplicationModule

class CustomApp : Application() {

    val db: ArtistAlbumsDb by lazy {
        ArtistAlbumsDb.getDataBase(this)
    }

    private val injector = DaggerApplicationComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()

    companion object {
        lateinit var instance: CustomApp private set
    }

    override fun onCreate() {
        super.onCreate().also {
            instance = this
            inject()
        }
    }

    private fun inject() {
        injector.inject(this)
    }
}