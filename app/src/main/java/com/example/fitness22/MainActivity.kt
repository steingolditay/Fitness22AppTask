package com.example.fitness22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness22.domain.Workouts
import com.example.fitness22.main_screens.Screen
import com.example.fitness22.ui.custom_views.HorizontalOptionBar
import com.example.fitness22.ui.custom_views.MyBottomNavigationBarView
import com.example.fitness22.ui.custom_views.WorkoutPlanHeader
import com.example.fitness22.ui.custom_views.WorkoutPlanListview
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
                        MyBottomNavigationBarView(
                            selectedScreen = selectedScreen,
                            onClick = { screen ->
                                selectedScreen = screen

                            }
                        )
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


                        HorizontalOptionBar()

                        WorkoutPlanHeader(
                            workouts = workouts,
                            selectedWorkoutDay = selectedWorkoutDay,
                            onDaySelected = {
                                selectedWorkoutDay = it
                            }
                        )

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(top = 16.dp)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            colorResource(R.color.background_dark),
                                            colorResource(R.color.background_dark).copy(0.4f)
                                        )
                                    )

                                )
                        ) {

                            WorkoutPlanListview(
                                selectedWorkoutDay = selectedWorkoutDay,
                                workouts = workouts
                            )

                            // action button
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
