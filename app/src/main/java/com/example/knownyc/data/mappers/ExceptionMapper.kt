package com.example.knownyc.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.knownyc.commons.AppError
import com.example.knownyc.commons.TAG
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun Throwable.toError(): AppError{
    Log.d(TAG, "mapping Exception: $this")
    val error = when(this){
        is retrofit2.HttpException -> "Unknown Response"
        is IOException -> "Network Error"
        else -> "Unknown Error"

    }
    return AppError(error, throwable = this)
}
