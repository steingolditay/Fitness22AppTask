package com.example.fitness22.ui.custom_views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitness22.R
import com.example.fitness22.main_screens.Screen

@Composable
fun MyBottomNavigationBarView(
    selectedScreen: Screen,
    onClick: (screen: Screen) -> Unit
){

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
                    onClick.invoke(screen)
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
}