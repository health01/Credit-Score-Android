package com.kst.creditscoreapp.network.model

import com.google.gson.annotations.SerializedName

data class CoachingSummary(
    @SerializedName("activeChat")
    val activeChat: Boolean? = null,

    @SerializedName("activeTodo")
    val activeTodo: Boolean? = null,

    @SerializedName("numberOfCompletedTodoItems")
    val numberOfCompletedTodoItems: Int? = null,

    @SerializedName("numberOfTodoItems")
    val numberOfTodoItems: Int? = null,

    @SerializedName("selected")
    val selected: Boolean? = null
)