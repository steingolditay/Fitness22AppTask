package com.example.fitness22.ui.custom_views

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness22.domain.Exercise


@Composable
fun ExerciseView(
    exercise: Exercise
) {
    val context = LocalContext.current

    fun getInfoString(): String {
        return with(exercise){
            "$amountOfSets sets x $repRange reps x $weightAmount lb"
        }

    }

    fun getImageBitmapByName(imageName: String): ImageBitmap {
        val inputStream = context.assets.open(imageName)
        return BitmapFactory.decodeStream(inputStream).asImageBitmap()
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)

    ){
        // exercise icon
        Image(
            bitmap = getImageBitmapByName(exercise.exerciseThumbnail),
            contentDescription = "",
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)) {
            Text(
                text = exercise.exerciseName,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = getInfoString(),
                color = Color.White
            )
        }

        // muscle icon
        Image(
            bitmap = getImageBitmapByName(exercise.muscleGroupImage),
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )


    }
}
