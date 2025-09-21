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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            val startScreen = Screen.MyWorkouts
            var selectedScreen by rememberSaveable { mutableStateOf(startScreen) }
            var selectedWorkoutDay by rememberSaveable { mutableIntStateOf(1) }

            Fitness22Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.background_main)
                            ),
                            title = {
                                Text(
                                    text = resources.getString(R.string.top_bar_title),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            },
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = colorResource(R.color.background_dark)
                        ) {
                            Screen.entries.forEach { screen ->
                                val isSelected = selectedScreen == screen
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = colorResource(R.color.yellow),
                                        selectedTextColor = colorResource(R.color.yellow),
                                        indicatorColor = Color.Transparent,
                                        unselectedIconColor = Color.White,
                                        unselectedTextColor = Color.White
                                    ),
                                    selected = isSelected,
                                    onClick = {
                                        selectedScreen = screen
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(screen.iconResource),
                                            modifier = Modifier.size(28.dp),
                                            contentDescription = screen.name,
                                        )

                                    },
                                    label = {
                                        screen.name
                                    }
                                )
                            }


                        }
                    },
                    containerColor = colorResource(R.color.background_main)
                ) { innerPadding ->


                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(colorResource(R.color.background_light))
                        )

                        // horizontal bar
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
                                        selectedWorkoutDay = it.day
                                    }
                                )
                            }
                        }

                        // header
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


                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(top = 16.dp)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                                .background(colorResource(R.color.background_dark))
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

                           Button(
                               shape = RoundedCornerShape(16.dp),
                               modifier = Modifier
                                   .padding(horizontal = 32.dp)
                                   .padding(bottom = 16.dp)
                                   .fillMaxWidth()
                                   .height(60.dp)
                                   .align(Alignment.BottomCenter),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = colorResource(R.color.yellow)
                               ),
                               onClick = {

                               }
                           ) {
                               Text(
                                   modifier = Modifier.fillMaxWidth(),
                                   text = stringResource(R.string.start_workout),
                                   color = Color.Black,
                                   fontSize = 24.sp,
                                   textAlign = TextAlign.Center,
                                   fontWeight = FontWeight.ExtraBold,
                                   fontStyle = FontStyle.Italic
                               )

                           }
                        }


                    }

                }
            }
        }
    }
}
