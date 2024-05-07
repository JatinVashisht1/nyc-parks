package com.example.knownyc.presentation.parks

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.knownyc.domain.models.NycPark

@Composable
fun NycParkCard(modifier: Modifier = Modifier, nycPark: NycPark) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .border(width = 2.dp, color = Color.DarkGray)
        ) {
            Text(text = nycPark.name)
            Text(text = nycPark.location)
        }
    }
}