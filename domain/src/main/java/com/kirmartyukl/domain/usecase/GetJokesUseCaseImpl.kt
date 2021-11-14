package com.kirmartyukl.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.kirmartyukl.domain.dto.ChuckNorrisJoke
import com.kirmartyukl.domain.repository.JokesRepository
import com.kirmartyukl.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetJokesUseCase {
    suspend fun execute(
        count: Int,
        jokesMutableLiveData: MutableLiveData<ArrayList<ChuckNorrisJoke>>
    ): Flow<LoadResult>
}

class GetJokesUseCaseImpl @Inject constructor(
    private val jokesRepository: JokesRepository
) : GetJokesUseCase {
    override suspend fun execute(
        count: Int,
        jokesMutableLiveData: MutableLiveData<ArrayList<ChuckNorrisJoke>>
    ): Flow<LoadResult> {
        return jokesRepository.loadChuckNorrisJokes(count, jokesMutableLiveData)
    }
}