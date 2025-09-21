package com.example.fitness22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitness22.domain.Workouts
import com.example.fitness22.main_screens.Screen
import com.example.fitness22.ui.custom_views.HorizontalBarItem
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
                                HorizontalBarItem(item)
                            }

                        }
                    }



                }
            }
        }
    }
}
