package org.com.testing.with.musicartistsample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.com.testing.with.musicartistsample.base.database.ArtistAlbumsDb
import org.com.testing.with.showArtist.model.db.dao.ArtistAlbumDao
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArtistAlbumDaoTest {

    lateinit var subject: ArtistAlbumDao
    lateinit var db: ArtistAlbumsDb

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ArtistAlbumsDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        subject = db.artistAlbumDao()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetEntity() = runBlocking {
        val data = ArtistAlbum(
            artistId = 1,
            artistName = "An artist that does not sing"
        )
        subject.insert(data)
        val allRecords = subject.getAll().first()
        Assert.assertEquals(allRecords[0].artistName, "An artist that does not sing")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByName() = runBlocking {
        val data1 = ArtistAlbum(
            artistId = 1,
            artistName = "An artist that does not sing",
            artistNameAsInput = "hey"
        )

        val data2 = ArtistAlbum(
            artistId = 2,
            artistName = "Nemo",
            artistNameAsInput = "nemo"
        )

        val data3 = ArtistAlbum(
            artistId = 3,
            artistName = "Nemo",
            artistNameAsInput = "nemo"
        )

        subject.insert(data1)
        subject.insert(data2)
        subject.insert(data3)

        val localData = subject.getAllByArtistName("nemo")

        assertEquals(2, localData.size)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}