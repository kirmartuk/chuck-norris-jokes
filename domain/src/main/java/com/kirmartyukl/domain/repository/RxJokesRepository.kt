package com.kirmartyukl.domain.repository

import com.kirmartyukl.domain.dto.ChuckNorrisJokeDTO
import io.reactivex.rxjava3.core.Single

interface RxJokesRepository {
    fun loadChuckNorrisJokes(count: Int): Single<ChuckNorrisJokeDTO>
}