package com.ase.pokedex

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val throwable: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.stateAsResultIn(
    scope: CoroutineScope,
): StateFlow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .catch { e -> emit(Result.Error(e)) }
    .stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Result.Loading
    )

inline fun <T> Result<T>.ifSuccess(block: (T) -> Unit) {
    if (this is Result.Success) {
        block(data)
    }
}
