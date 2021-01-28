package org.com.testing.with.showArtist.model.repository

import androidx.annotation.WorkerThread
import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.flow.Flow
import org.com.testing.with.showArtist.model.db.dao.ArtistAlbumDao
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum

class ArtistAlbumRepository(private val dao: ArtistAlbumDao) {

    @WorkerThread
    suspend fun insert(data: ArtistAlbum) {
        dao.insert(data)
    }

    @WorkerThread
    suspend fun delete(data: ArtistAlbum) {
        dao.delete(data)
    }

    @WorkerThread
    fun getByArtistName(artistName: String) = dao.getByArtistName(artistName)

    @WorkerThread
    fun getAll() = dao.getAll()

    @WorkerThread
    fun _getByArtistName(artistName: String): List<ArtistAlbum>? {
        val query = "SELECT * FROM ArtistAlbum WHERE artistName LIKE '%$artistName%' COLLATE NOCASE"
        return dao._getByArtistName(SimpleSQLiteQuery(query))
    }

    @WorkerThread
    suspend fun getAllByArtistName(artistName: String): List<ArtistAlbum> =
        dao.getAllByArtistName(artistName)

    @WorkerThread
    fun getByArtistId(id: Int): Flow<List<ArtistAlbum>> = dao.getByArtistId(id)

    @WorkerThread
    suspend fun update(data: ArtistAlbum) {
        dao.update(data)
    }

}