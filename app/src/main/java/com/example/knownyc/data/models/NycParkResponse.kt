package com.example.knownyc.data.models

data class NycParkResponse(
    val signName: String?,
    val location: String?,
    val waterfront: Boolean? = false,
    val url: String?,
)
