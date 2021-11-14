package com.kirmartyukl.data.rx

import com.kirmartyukl.data.api.JokesApiService
import com.kirmartyukl.domain.dto.ChuckNorrisJokeDTO
import com.kirmartyukl.domain.repository.RxJokesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RxJokesRepositoryImpl @Inject constructor(
    private val jokesApiService: JokesApiService
) : RxJokesRepository {
    override fun loadChuckNorrisJokes(
        count: Int
    ): Single<ChuckNorrisJokeDTO> {
        return jokesApiService.getFixedRandomJokesRx(count)
    }
}