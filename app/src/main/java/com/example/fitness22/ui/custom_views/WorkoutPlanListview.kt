package com.example.fitness22.ui.custom_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness22.domain.Workouts

@Composable
fun WorkoutPlanListview(
    selectedWorkoutDay: Int,
    workouts: Workouts
) {

    val workoutPlan = workouts.workouts.first { it.day == selectedWorkoutDay }.workout

    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "${workoutPlan.size} Exercises - ${(20..120).random()} Min - ${(100..800).random()} Cal",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold

                )
            }
        }

        items(
            items = workoutPlan,
            key = { exercise ->
                exercise.exerciseId
            }
        ) { exercise ->
            ExerciseView(exercise)

        }

    }
}