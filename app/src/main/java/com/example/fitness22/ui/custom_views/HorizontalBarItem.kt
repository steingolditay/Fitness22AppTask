package com.example.fitness22.ui.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitness22.R


@Composable
fun HorizontalBarItem(
    title: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.Gray)
            .padding(2.dp)

    ){
        Text(
            text = "Some title",
            color = Color.White
        )
        Icon(
            painter = painterResource(R.drawable.arrow_down),
            contentDescription = title,
            tint = Color.White
        )
    }
}

@Composable
@Preview
fun HorizontalBarItemPreview(){
    HorizontalBarItem("some title")
}