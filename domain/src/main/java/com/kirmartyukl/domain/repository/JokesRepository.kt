package com.kirmartyukl.domain.repository

import androidx.lifecycle.MutableLiveData
import com.kirmartyukl.domain.dto.ChuckNorrisJoke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    suspend fun loadChuckNorrisJokes(
        count: Int,
        jokesMutableLiveData: MutableLiveData<ArrayList<ChuckNorrisJoke>>
    ): Flow<LoadResult>
}