package com.kst.creditscoreapp.model

import java.io.Serializable

data class LocalScoreDetail(
    val score: String,
    val angle: Int,
    val color: Int,
    val maxScore: String
) : Serializable
