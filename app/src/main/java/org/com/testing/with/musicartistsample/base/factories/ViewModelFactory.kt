package org.com.testing.with.musicartistsample.base.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.com.testing.with.ui.viewmodels.MainViewModel
import java.lang.IllegalStateException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel() as T
        }
        throw IllegalStateException("View Model does not exist")
    }
}