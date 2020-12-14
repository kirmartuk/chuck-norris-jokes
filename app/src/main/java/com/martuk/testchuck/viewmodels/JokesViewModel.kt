package com.martuk.testchuck.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martuk.testchuck.entities.ChuckNorrisJoke
import com.martuk.testchuck.api.JokesRepository
import com.martuk.testchuck.api.LoadResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class JokesViewModel : ViewModel() {

    val jokes: MutableLiveData<ArrayList<ChuckNorrisJoke>> = MutableLiveData()
    val progress: MutableLiveData<LoadResult> = MutableLiveData()

    fun loadChuckNorrisJokes(count: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            JokesRepository(jokes).loadChuckNorrisJokes(count).collect {
                progress.postValue(it)
            }
        }
    }
}