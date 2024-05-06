package com.example.knownyc.presentation.ui.util.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    showBackIcon: Boolean = false,
    onBackClick: () -> Unit = {},
    showSearchIcon: Boolean = false,
    onActionClick: () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                navigationIcon = {
                    if (showBackIcon) {
                        IconButton(onClick = {onBackClick()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }, actions = {
                    if (showSearchIcon) {

                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                modifier = Modifier
                                    .padding(12.dp)
                                    .clickable {
                                        onActionClick()
                                    }
                            )
                        }
                    }, modifier = modifier.shadow(
                        elevation = 4.dp,
                        spotColor = MaterialTheme.colorScheme.surfaceTint
                    )
                    )
                }, modifier = Modifier.fillMaxSize()
            )

    {
        paddingValues -> content(paddingValues)
    }
        }

