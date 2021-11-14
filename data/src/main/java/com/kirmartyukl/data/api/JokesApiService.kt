package com.kirmartyukl.data.api

import com.kirmartyukl.domain.dto.ChuckNorrisJokeDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApiService {
    @GET("/jokes/random/{count}")
    suspend fun getFixedRandomJokes(@Path("count") count: Int): ChuckNorrisJokeDTO

    @GET("/jokes/random/{count}")
    fun getFixedRandomJokesRx(@Path("count") count: Int): Single<ChuckNorrisJokeDTO>
}