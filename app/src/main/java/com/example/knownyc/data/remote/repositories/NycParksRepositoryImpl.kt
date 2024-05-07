package com.example.knownyc.data.remote.repositories

import com.example.knownyc.data.mappers.toNycParkModel
import com.example.knownyc.domain.apistate.ApiState
import com.example.knownyc.domain.models.Borough
import com.example.knownyc.domain.models.NycPark
import com.example.knownyc.domain.repositories.NycParksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NycParksRepositoryImpl @Inject constructor(
    private val nycParksApi: NycOpenDataApiService,
) : NycParksRepository {
    override suspend fun getParkList(borough: String): Flow<ApiState<List<NycPark>>> = flow {
        try {
            emit(ApiState.Loading<List<NycPark>>())

            val nycParkDtoList = nycParksApi.getNycParks(borough = borough)
            val nycParkDomainList = nycParkDtoList.map { nycParkDto ->
                nycParkDto.toNycParkModel()
            }

            emit(ApiState.Success<List<NycPark>>(successData = nycParkDomainList))
        } catch (exception: Exception) {
            emit(
                ApiState.Error<List<NycPark>>(
                    exception.message ?: "Something went wrong. Please try again later.",
                )
            )
        }
    }
}
