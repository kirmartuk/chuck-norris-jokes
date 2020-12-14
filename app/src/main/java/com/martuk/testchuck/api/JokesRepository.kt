package com.martuk.testchuck.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.martuk.testchuck.entities.ChuckNorrisJoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Simple repository for downloading awesome jokes about Chuck Norris
 * @author kirmartuk
 */
class JokesRepository(
    private val mJokesMutableLiveData: MutableLiveData<ArrayList<ChuckNorrisJoke>>
) {

    companion object {
        const val BASE_URL = "http://api.icndb.com/"
    }

    /**
     * This method download jokes by count + track progress
     */
    suspend fun loadChuckNorrisJokes(count: Int): Flow<LoadResult> {
        return flow {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val jokesApiService = retrofit.create(JokesApiService::class.java)
                mJokesMutableLiveData.postValue(jokesApiService.getFixedRandomJokes(count).value)

                emit(LoadResult.Success)
            } catch (ioException: IOException) {
                ioException.message?.let {
                    emit(LoadResult.Error(it))
                    Log.e("ioException", it)
                }
            } catch (httpException: HttpException) {
                httpException.message?.let {
                    emit(LoadResult.Error(it))
                    Log.e("httpException", it)
                }
            }
        }
    }

}