package com.example.knownyc.data.mappers

import com.example.knownyc.data.models.NycParkResponse
import com.example.knownyc.domain.models.NycPark

fun NycParkResponse.toNycParkModel() = NycPark(
    name = signName ?: "",
    location = location ?: "",
    isWaterfront = waterfront ?: false,
    url = url ?: "",
)