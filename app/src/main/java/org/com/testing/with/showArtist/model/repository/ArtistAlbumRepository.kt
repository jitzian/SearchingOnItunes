package org.com.testing.with.showArtist.model.repository

import androidx.annotation.WorkerThread
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
    suspend fun getByArtistName(artistName: String) = dao.getByArtistName(artistName)

    @WorkerThread
    suspend fun getByArtistId(id: Int): Flow<List<ArtistAlbum>> = dao.getByArtistId(id)

    @WorkerThread
    suspend fun update(data: ArtistAlbum) {
        dao.update(data)
    }

}