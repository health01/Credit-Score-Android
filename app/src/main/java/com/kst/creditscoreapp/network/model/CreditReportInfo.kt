package com.kst.creditscoreapp.network.model

import com.google.gson.annotations.SerializedName

data class CreditReportInfo(
    @SerializedName("changeInLongTermDebt")
    val changeInLongTermDebt: Int? = null,

    @SerializedName("changeInShortTermDebt")
    val changeInShortTermDebt: Int? = null,

    @SerializedName("changedScore")
    val changedScore: Int? = null,

    @SerializedName("clientRef")
    val clientRef: String? = null,

    @SerializedName("currentLongTermCreditLimit")
    val currentLongTermCreditLimit: Any? = null,

    @SerializedName("currentLongTermCreditUtilisation")
    val currentLongTermCreditUtilisation: Any? = null,

    @SerializedName("currentLongTermDebt")
    val currentLongTermDebt: Int? = null,

    @SerializedName("currentLongTermNonPromotionalDebt")
    val currentLongTermNonPromotionalDebt: Int? = null,

    @SerializedName("currentShortTermCreditLimit")
    val currentShortTermCreditLimit: Int? = null,

    @SerializedName("currentShortTermCreditUtilisation")
    val currentShortTermCreditUtilisation: Int? = null,

    @SerializedName("currentShortTermDebt")
    val currentShortTermDebt: Int? = null,

    @SerializedName("currentShortTermNonPromotionalDebt")
    val currentShortTermNonPromotionalDebt: Int? = null,

    @SerializedName("daysUntilNextReport")
    val daysUntilNextReport: Int? = null,

    @SerializedName("equifaxScoreBand")
    val equifaxScoreBand: Int? = null,

    @SerializedName("equifaxScoreBandDescription")
    val equifaxScoreBandDescription: String? = null,

    @SerializedName("hasEverBeenDelinquent")
    val hasEverBeenDelinquent: Boolean? = null,

    @SerializedName("hasEverDefaulted")
    val hasEverDefaulted: Boolean? = null,

    @SerializedName("maxScoreValue")
    val maxScoreValue: Int? = null,

    @SerializedName("minScoreValue")
    val minScoreValue: Int? = null,

    @SerializedName("monthsSinceLastDefaulted")
    val monthsSinceLastDefaulted: Int? = null,

    @SerializedName("monthsSinceLastDelinquent")
    val monthsSinceLastDelinquent: Int? = null,

    @SerializedName("numNegativeScoreFactors")
    val numNegativeScoreFactors: Int? = null,

    @SerializedName("numPositiveScoreFactors")
    val numPositiveScoreFactors: Int? = null,

    @SerializedName("percentageCreditUsed")
    val percentageCreditUsed: Int? = null,

    @SerializedName("percentageCreditUsedDirectionFlag")
    val percentageCreditUsedDirectionFlag: Int? = null,

    @SerializedName("score")
    val score: Int? = null,

    @SerializedName("scoreBand")
    val scoreBand: Int? = null,

    @SerializedName("status")
    val status: String? = null
)