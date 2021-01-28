package org.com.testing.with.showArtist.model.db.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum

@Dao
interface ArtistAlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ArtistAlbum)

    @Delete
    suspend fun delete(data: ArtistAlbum)

    @Query("SELECT * FROM ArtistAlbum WHERE artistName LIKE :artistName")
    fun getByArtistName(artistName: String): Flow<List<ArtistAlbum>?>

    @Query("SELECT * FROM ArtistAlbum WHERE artistNameAsInput LIKE :artistNameAsInput")
    suspend fun getAllByArtistName(artistNameAsInput: String): List<ArtistAlbum>

    @RawQuery
    fun _getByArtistName(query: SupportSQLiteQuery): List<ArtistAlbum>?


    @Query("SELECT * FROM ArtistAlbum ORDER BY id ASC")
    fun getAll(): Flow<List<ArtistAlbum>>

    @Query("SELECT * FROM ArtistAlbum WHERE id=:id")
    fun getByArtistId(id: Int): Flow<List<ArtistAlbum>>

    @Update
    suspend fun update(data: ArtistAlbum)

}