package org.com.testing.with.showArtist.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
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

    fun _fetchData(artistName: String?) = viewModelScope.launch {
//        artistName?.let { safeArtistName ->
//            viewModelScope.launch {
//                val queryResult = withContext(Dispatchers.IO) {
//                    repository.getByArtistName(safeArtistName)
//                }
//                if (queryResult.asLiveData().value != null) {
//                    _artistResult.postValue(queryResult.asLiveData().value)
//                } else {
//
//                }
//            }
//
//
//        }

        if (artistName != null) {
            val localResult = repository.getByArtistName(artistName).asLiveData()
            if (localResult.value != null) {
                _artistResult.postValue(localResult.value)
            } else {
                val queryMap = mutableMapOf(
                    "term" to artistName
                )
                val resultOfFetchedData = withContext(Dispatchers.IO) {
                    restApi.fetchRemoteData(queryMap)
                }

                resultOfFetchedData.results?.let { safeListOfResults ->
                    safeListOfResults.forEach {
                        val tempArtistAlbum = ArtistAlbum(
                            artistId = it.artistId,
                            artistName = it.artistName,
                            trackName = it.trackName,
                            releaseDate = it.releaseDate,
                            primaryGenreName = it.primaryGenreName,
                            trackPrice = it.trackPrice
                        )
                        repository.insert(tempArtistAlbum)
                    }
                    withContext(Dispatchers.IO) {
                        _artistResult.postValue(
                            repository.getByArtistName(artistName).asLiveData().value
                        )
                    }
                }
            }
        }
    }

    fun fetchData(artistName: String?) {
        if (artistName != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    repository.getByArtistName(artistName).collect { listOfArtistAlbums ->
                        if (listOfArtistAlbums.isNotEmpty()) {
                            Log.e(TAG, "fetchData::NOT EMPTY::${listOfArtistAlbums.size}")
                            _artistResult.postValue(listOfArtistAlbums)
                        } else {
                            Log.e(TAG, "fetchData::EMPTY::")
                            val queryMap = mutableMapOf(
                                "term" to artistName
                            )
                            val resultRemoteData = restApi.fetchRemoteData(queryMap)
                            resultRemoteData.results.apply {
                                this?.forEach {
                                    val tempArtistAlbum = ArtistAlbum(
                                        artistId = it.artistId,
                                        artistName = it.artistName,
                                        trackName = it.trackName,
                                        releaseDate = it.releaseDate,
                                        primaryGenreName = it.primaryGenreName,
                                        trackPrice = it.trackPrice
                                    )
                                    repository.insert(tempArtistAlbum)
                                }

                                _artistResult.postValue(
                                    //TODO: Should I used collect instead of asLiveData().value??
                                    repository.getByArtistName(artistName).asLiveData().value
                                )
                            }
                        }

                    }
                }
            }
        }
    }

    //TODO: Implement
    fun fetchRemoteData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                val queryMap = mutableMapOf(
                    "term" to "Linkin Park"
                )
                restApi.fetchRemoteData(queryMap)
            }
            Log.e(TAG, "fetchRemoteData::${result.resultCount}")
        }
    }

}