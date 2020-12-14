package com.martuk.testchuck.api

sealed class LoadResult {
    object Success : LoadResult()
    data class Error(val message: String) : LoadResult()

}