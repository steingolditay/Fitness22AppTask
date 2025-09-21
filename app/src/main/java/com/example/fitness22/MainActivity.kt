package com.example.fitness22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitness22.domain.Workouts
import com.example.fitness22.main_screens.Screen
import com.example.fitness22.ui.custom_views.ExerciseView
import com.example.fitness22.ui.custom_views.HorizontalBarItemView
import com.example.fitness22.ui.custom_views.WorkoutDayButtonView
import com.example.fitness22.ui.theme.Fitness22Theme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val workoutsJsonString = assets.open("workouts.json").readBytes().decodeToString()
        val workouts = Gson().fromJson(workoutsJsonString, Workouts::class.java)

        setContent {
            val startScreen =  Screen.MyWorkouts
            var selectedScreen by rememberSaveable { mutableStateOf(startScreen) }
            var selectedWorkoutDay by rememberSaveable { mutableIntStateOf(1) }

            Fitness22Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.DarkGray
                            ),
                            title = {
                                Text(resources.getString(R.string.top_bar_title))
                            },
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.DarkGray
                        ) {
                            Screen.entries.forEach { screen ->
                                val isSelected = selectedScreen == screen
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        selectedScreen = screen
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(screen.iconResource),
                                            modifier = Modifier.size(24.dp),
                                            contentDescription = screen.name,
                                            tint = if (isSelected) Color.Black else Color.White
                                        )

                                    },
                                    label = {
                                        screen.name
                                    }
                                )
                            }


                        }
                    },
                    ) { innerPadding ->


                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color.Red)
                    ) {

                        // horizontal bar
                        val horizontalBarItems = listOf(
                            "Muscles (16)", "45-60 Min", "Schedule", "Basic Exercises", "Equipment (64)", "Goals (1)"
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Red)
                        ) {
                            items(horizontalBarItems){ item ->
                                HorizontalBarItemView(item)
                            }

                        }

                        // days bar
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            workouts.workouts.forEach {
                                WorkoutDayButtonView(
                                    dayNumber = it.day,
                                    isSelected = selectedWorkoutDay == it.day,
                                    onClick = {
                                        selectedWorkoutDay = it.day
                                    }
                                )
                            }
                        }

                        // header
                        Row {
                            Spacer(modifier = Modifier.weight(0.2f))

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(0.6f)
                            ) {
                                Text("first title")
                                Text("second title")
                                Text("third title")
                            }

                            Column(modifier = Modifier.weight(0.2f)){
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(R.drawable.ic_edit),
                                    contentDescription = "action button",
                                    tint = Color.White
                                )
                            }
                        }




                        // workout plan
                        val workoutPlan = workouts.workouts.first { it.day == selectedWorkoutDay }.workout
                        LazyColumn {
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("day workout header")
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

                }
            }
        }
    }
}
