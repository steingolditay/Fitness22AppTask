package com.example.fitness22.ui.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness22.R
import com.example.fitness22.domain.Workouts

@Composable
fun WorkoutPlanHeader(
    workouts: Workouts,
    selectedWorkoutDay: Int,
    onDaySelected: (day: Int) -> Unit
) {

    // days bar
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        workouts.workouts.forEach {
            WorkoutDayButtonView(
                dayNumber = it.day,
                isSelected = selectedWorkoutDay == it.day,
                onClick = {
                    onDaySelected.invoke(it.day)
                }
            )
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(0.2f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Week 1/5 - Foundations",
                color = colorResource(R.color.light_blue)
            )
            Text(
                text = "UPCOMING WORKOUT",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Push",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .weight(0.2f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(R.color.background_dark))
                .padding()
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = "action button",
                tint = Color.White
            )
        }
    }
}