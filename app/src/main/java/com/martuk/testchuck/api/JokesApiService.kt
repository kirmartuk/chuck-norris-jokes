package com.martuk.testchuck.api

import com.martuk.testchuck.entities.ChuckNorrisJokeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApiService {
    @GET("/jokes/random/{count}")
    suspend fun getFixedRandomJokes(@Path("count") count: Int): ChuckNorrisJokeDTO
}