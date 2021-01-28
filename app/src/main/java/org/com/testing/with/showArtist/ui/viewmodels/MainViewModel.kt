package org.com.testing.with.showArtist.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.com.testing.with.musicartistsample.CustomApp
import org.com.testing.with.musicartistsample.base.BaseViewModel
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum
import org.com.testing.with.showArtist.model.repository.ArtistAlbumRepository

class MainViewModel : BaseViewModel() {

    private val repository by lazy {
        ArtistAlbumRepository(CustomApp.instance.db.artistAlbumDao())
    }

    init {
        TAG = MainViewModel::class.java.simpleName
    }

    private val _artistResult = MutableLiveData<List<ArtistAlbum>>()

    val artistResult: LiveData<List<ArtistAlbum>>
        get() = _artistResult

    fun fetchData(artistName: String) = viewModelScope.launch {
        val localData = repository.getAllByArtistName(artistName)
        if (localData.isNotEmpty()) {
            _artistResult.postValue(localData)
        } else {
            if (artistName.isNotBlank() || artistName.isNotEmpty()) {

                val remoteData = withContext(Dispatchers.IO) {
                    restApi.fetchRemoteData(mapOf("term" to artistName))
                }

                if (remoteData.results?.isNotEmpty() == true) {
                    remoteData.results.forEach {
                        val tempArtistAlbum = ArtistAlbum(
                            artistId = it.artistId,
                            artistName = it.artistName,
                            artistNameAsInput = artistName,
                            trackName = it.trackName,
                            releaseDate = it.releaseDate,
                            primaryGenreName = it.primaryGenreName,
                            trackPrice = it.trackPrice,
                            artworkUrl100 = it.artworkUrl100,
                            artworkUrl60 = it.artworkUrl60,
                            artworkUrl30 = it.artworkUrl30
                        )
                        repository.insert(tempArtistAlbum)
                    }
                    _artistResult.postValue(repository.getAllByArtistName(artistName))
                }
            }
        }
    }

}