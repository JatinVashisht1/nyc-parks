package com.example.knownyc.domain.repositories

import com.example.knownyc.domain.apistate.ApiState
import com.example.knownyc.domain.models.Borough
import com.example.knownyc.domain.models.NycPark
import kotlinx.coroutines.flow.Flow


interface NycParksRepository {
    suspend fun getParkList(borough: String): Flow<ApiState<List<NycPark>>>
}