package org.com.testing.with.showArtist.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.com.testing.with.musicartistsample.CustomApp
import org.com.testing.with.musicartistsample.base.BaseViewModel
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum
import org.com.testing.with.showArtist.model.repository.ArtistAlbumRepository
import retrofit2.HttpException

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

    fun fetchData(artistName: String) = viewModelScope.launch(Dispatchers.IO) {
        val localData = repository.getAllByArtistName(artistName)
        if (localData.isNotEmpty()) {
            _artistResult.postValue(localData)
        } else {
            try {
                val remoteData = restApi.fetchRemoteData(mapOf("term" to artistName))

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

            } catch (httpEx: HttpException) {
                Log.e(TAG, "fetchData::${httpEx.message()}")
            }
        }
    }

}