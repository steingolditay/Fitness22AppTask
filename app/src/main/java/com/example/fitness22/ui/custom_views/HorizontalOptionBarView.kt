package com.example.fitness22.ui.custom_views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalOptionBar(){
    val horizontalBarItems = listOf(
        "Muscles (16)",
        "45-60 Min",
        "Schedule",
        "Basic Exercises",
        "Equipment (64)",
        "Goals (1)"
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(horizontalBarItems) { item ->
            HorizontalBarItemView(item)
        }
    }
}