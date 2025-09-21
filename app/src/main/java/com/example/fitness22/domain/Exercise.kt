package com.example.fitness22.domain

import com.google.gson.annotations.SerializedName


data class Exercise(
    @SerializedName("exercise_id")
    val exerciseId: Int,

    @SerializedName("exercise_name")
    val exerciseName: String,

    @SerializedName("exercise_thumbnail")
    val exerciseThumbnail: String,

    @SerializedName("muscle_group")
    val muscleGroup: String,

    @SerializedName("muscle_group_image")
    val muscleGroupImage: String,

    @SerializedName("amount_of_sets")
    val amountOfSets: Int,

    @SerializedName("rep_range")
    val repRange: String,

    @SerializedName("weight_amount")
    val weightAmount: String?
)