package com.kst.creditscoreapp.network.model

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("accountIDVStatus")
    val accountIDVStatus: String? = null,

    @SerializedName("augmentedCreditScore")
    val augmentedCreditScore: Any? = null,

    @SerializedName("coachingSummary")
    val coachingSummary: CoachingSummary? = null,

    @SerializedName("creditReportInfo")
    val creditReportInfo: CreditReportInfo? = null,

    @SerializedName("dashboardStatus")
    val dashboardStatus: String? = null,

    @SerializedName("personaType")
    val personaType: String? = null
)