package com.kirmartyukl.domain.usecase

import com.kirmartyukl.domain.dto.ChuckNorrisJokeDTO
import com.kirmartyukl.domain.repository.RxJokesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface RxGetJokesUseCase {
    fun execute(count: Int): Single<ChuckNorrisJokeDTO>
}

class RxGetJokesUseCaseImpl @Inject constructor(private val rxJokesRepository: RxJokesRepository) :
    RxGetJokesUseCase {
    override fun execute(count: Int): Single<ChuckNorrisJokeDTO> {
        return rxJokesRepository.loadChuckNorrisJokes(count)
    }
}