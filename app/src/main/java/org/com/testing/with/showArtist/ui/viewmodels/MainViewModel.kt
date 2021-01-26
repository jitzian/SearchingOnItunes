package org.com.testing.with.ui.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.com.testing.with.musicartistsample.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    init {
        TAG = MainViewModel::class.java.simpleName
    }

    //TODO: Implement local cache functionality
    fun checkLocalData() {

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