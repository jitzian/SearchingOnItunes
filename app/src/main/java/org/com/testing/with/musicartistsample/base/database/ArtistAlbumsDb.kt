package org.com.testing.with.musicartistsample.base.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.com.testing.with.musicartistsample.constants.GlobalConstants
import org.com.testing.with.showArtist.model.db.dao.ArtistAlbumDao
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum

@Database(
    entities = [
        ArtistAlbum::class
    ],
    version = GlobalConstants.versionDb,
    exportSchema = true
)
abstract class ArtistAlbumsDb : RoomDatabase() {

    abstract fun artistAlbumDao(): ArtistAlbumDao

    companion object {

        @Volatile
        private var INSTANCE: ArtistAlbumsDb? = null

        fun getDataBase(context: Context): ArtistAlbumsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtistAlbumsDb::class.java,
                    GlobalConstants.nameDb
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}