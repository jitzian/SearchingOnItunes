package org.com.testing.with.showArtist.model.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArtistAlbum")
data class ArtistAlbum(
    @ColumnInfo(name = "artistId") val artistId: Int?,
    @ColumnInfo(name = "artistName") val artistName: String? = "",
    @ColumnInfo(name = "artistViewUrl") val artistViewUrl: String? = "",
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String? = "",
    @ColumnInfo(name = "artworkUrl30") val artworkUrl30: String? = "",
    @ColumnInfo(name = "artworkUrl60") val artworkUrl60: String? = "",
    @ColumnInfo(name = "collectionArtistId") val collectionArtistId: Int? = 0,
    @ColumnInfo(name = "collectionArtistName") val collectionArtistName: String? = "",
    @ColumnInfo(name = "collectionArtistViewUrl") val collectionArtistViewUrl: String? = "",
    @ColumnInfo(name = "collectionCensoredName") val collectionCensoredName: String? = "",
    @ColumnInfo(name = "collectionExplicitness") val collectionExplicitness: String? = "",
    @ColumnInfo(name = "collectionId") val collectionId: Int? = 0,
    @ColumnInfo(name = "collectionName") val collectionName: String? = "",
    @ColumnInfo(name = "collectionPrice") val collectionPrice: Double? = 0.0,
    @ColumnInfo(name = "collectionViewUrl") val collectionViewUrl: String? = "",
    @ColumnInfo(name = "contentAdvisoryRating") val contentAdvisoryRating: String? = "",
    @ColumnInfo(name = "copyright") val copyright: String? = "",
    @ColumnInfo(name = "country") val country: String? = "",
    @ColumnInfo(name = "currency") val currency: String? = "",
    @ColumnInfo(name = "description") val description: String? = "",
    @ColumnInfo(name = "discCount") val discCount: Int? = 0,
    @ColumnInfo(name = "discNumber") val discNumber: Int? = 0,
    @ColumnInfo(name = "isStreamable") val isStreamable: Boolean? = false,
    @ColumnInfo(name = "kind") val kind: String? = "",
    @ColumnInfo(name = "previewUrl") val previewUrl: String? = "",
    @ColumnInfo(name = "primaryGenreName") val primaryGenreName: String? = "",
    @ColumnInfo(name = "releaseDate") val releaseDate: String? = "",
    @ColumnInfo(name = "trackCensoredName") val trackCensoredName: String? = "",
    @ColumnInfo(name = "trackCount") val trackCount: Int? = 0,
    @ColumnInfo(name = "trackExplicitness") val trackExplicitness: String? = "",
    @ColumnInfo(name = "trackId") val trackId: Int? = 0,
    @ColumnInfo(name = "trackName") val trackName: String? = "",
    @ColumnInfo(name = "trackNumber") val trackNumber: Int? = 0,
    @ColumnInfo(name = "trackPrice") val trackPrice: Double? = 0.0,
    @ColumnInfo(name = "trackTimeMillis") val trackTimeMillis: Int? = 0,
    @ColumnInfo(name = "trackViewUrl") val trackViewUrl: String? = "",
    @ColumnInfo(name = "wrapperType") val wrapperType: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}