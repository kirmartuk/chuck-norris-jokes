package com.kirmartyukl.data.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kirmartyukl.domain.dto.ChuckNorrisJoke
import com.kirmartyukl.domain.repository.JokesRepository
import com.kirmartyukl.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Simple repository for downloading awesome jokes about Chuck Norris
 * @author kirmartuk
 */
class JokesRepositoryImpl @Inject constructor(private val jokesApiService: JokesApiService) :
    JokesRepository {

    /**
     * This method download jokes by count + track progress
     */
    override suspend fun loadChuckNorrisJokes(
        count: Int,
        mJokesMutableLiveData: MutableLiveData<ArrayList<ChuckNorrisJoke>>
    ): Flow<LoadResult> {
        return flow {
            try {
                emit(LoadResult.Loading)
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