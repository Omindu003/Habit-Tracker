package com.example.madlab3.models

import java.io.Serializable

data class MoodEntry(
    val id: Long,
    val emoji: String,
    val mood: String,
    val date: String,
    val time: String,
    val note: String,
    val colorResId: Int
) : Serializable