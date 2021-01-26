package org.com.testing.with.showArtist.model.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum

@Dao
interface ArtistAlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ArtistAlbum)

    @Delete
    suspend fun delete(data: ArtistAlbum)

    @Query("SELECT * FROM ArtistAlbum WHERE artistName=:artistName")
    suspend fun getByArtistName(artistName: String): Flow<List<ArtistAlbum>>

    @Query("SELECT * FROM ArtistAlbum WHERE id=:id")
    suspend fun getByArtistId(id: Int): Flow<List<ArtistAlbum>>

    @Update
    suspend fun update(data: ArtistAlbum)

}