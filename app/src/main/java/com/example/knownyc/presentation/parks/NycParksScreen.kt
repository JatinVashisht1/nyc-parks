package com.example.knownyc.presentation.parks

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NycParksScreenParent(
    searchText: String,
    nycParksViewModel: NycParksViewModel = hiltViewModel(),
) {
    val nycParksScreenUIState by nycParksViewModel.parkListScreenState.collectAsState()

    val tag = "NycParksScreenParent"
    Log.d(tag, "nycParksScreenUIState: $nycParksScreenUIState")

    NycParksScreen(
        modifier = Modifier.fillMaxSize(),
        nycParksScreenUIState = nycParksScreenUIState,
    )
}

@Composable
fun NycParksScreen(modifier: Modifier = Modifier, nycParksScreenUIState: NycParksUIState) {
    val lazyListState = rememberLazyListState()

    LazyColumn(modifier = modifier, state = lazyListState) {
        items(nycParksScreenUIState.parkList) {nycPark ->
            NycParkCard(nycPark = nycPark)
        }
    }
}
