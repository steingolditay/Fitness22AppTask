package com.example.fitness22.ui.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness22.R

@Composable
fun WorkoutDayButtonView(
    dayNumber: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .widthIn(70.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                colorResource(
                    if (isSelected){
                        R.color.background_dark
                    } else {
                        R.color.background_light
                    }
                )

            )
            .then(
                if (!isSelected){
                    Modifier.clickable {
                        onClick.invoke()
                    }
                } else {
                    Modifier
                }
            )
            .padding(8.dp)


    ){
        if (isSelected){
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.check),
                contentDescription =  "Day $dayNumber",
                tint = colorResource(R.color.light_blue)
            )

        } else {
            Text(
                text = "Day $dayNumber",
                fontWeight = FontWeight.Black,
                color = colorResource(R.color.light_blue)
            )
        }

    }
}