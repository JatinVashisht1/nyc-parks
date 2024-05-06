package com.example.knownyc.data.mappers

import com.example.knownyc.data.local.provider.AssetsProvider
import com.example.knownyc.domain.models.Borough
import org.json.JSONObject

//Map JSON to Borough data class

suspend fun boroughsMapper(
    jsonObj: JSONObject,
    localAssetsProvider: AssetsProvider,
): List<Borough> {
    val jsonArray = jsonObj.getJSONArray("boroughs")
    val boroughs = mutableListOf<Borough>()

    for (i in 0 until jsonArray.length()) {
        val boroughObj = jsonArray.getJSONObject(i)

        val borough = Borough(
            boroCode = boroughObj.getString("borough").first(),
            name = boroughObj.getString("shortName"),
            longName = boroughObj.getString("fullName"),
            image = localAssetsProvider.getDrawableResourceId(boroughObj.getString("imageFilename")),


            )

        boroughs.add(borough)

    }

    return boroughs

}