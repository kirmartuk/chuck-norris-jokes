package com.martuk.testchuck.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirmartyukl.domain.dto.ChuckNorrisJoke
import com.kirmartyukl.domain.repository.LoadResult
import com.kirmartyukl.domain.usecase.GetJokesUseCase
import com.kirmartyukl.domain.usecase.RxGetJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val jokesUseCase: GetJokesUseCase,
    private val rxGetJokesUseCase: RxGetJokesUseCase
) : ViewModel() {

    val jokes: MutableLiveData<ArrayList<ChuckNorrisJoke>> = MutableLiveData()
    val progress: MutableLiveData<LoadResult> = MutableLiveData()

    fun loadChuckNorrisJokes(count: Int, useRxJava: Boolean = true) {
        if (useRxJava) {
            rxGetJokesUseCase.execute(count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.postValue(LoadResult.Loading)
                }
                .doOnSuccess {
                    progress.postValue(LoadResult.Success)
                }
                .doOnError {
                    progress.postValue(LoadResult.Error(it.message.toString()))
                }
                .subscribe({
                    jokes.postValue(it.value)
                }, {
                    progress.postValue(LoadResult.Error(it.message.toString()))
                })
        } else {
            viewModelScope.launch(Dispatchers.Default) {
                jokesUseCase.execute(count, jokes).collect {
                    progress.postValue(it)
                }
            }
        }
    }
}