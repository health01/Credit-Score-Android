package com.kst.creditscoreapp.ui.main.credit

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kst.creditscoreapp.network.model.CreditReportInfo
import com.kst.creditscoreapp.model.LocalScoreDetail
import com.kst.creditscoreapp.network.CreditState
import com.kst.creditscoreapp.repository.CreditRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreditFragmentViewModel @Inject constructor(
    private val creditRepository: CreditRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO


) : ViewModel() {
    private val _uiState = MutableStateFlow<CreditState>(CreditState.Error(null))
    val uiState: StateFlow<CreditState> = _uiState

    fun getNewData() =

        viewModelScope.launch {

            creditRepository.getDetailFlow()
                .flowOn(dispatcher)
                .catch { exception ->
                    _uiState.value = CreditState.Error(exception)
                }
                .collect { credit ->
                    if (credit.creditReportInfo != null) {

                        credit.creditReportInfo.apply {

                            if (score == null || score < 0 || maxScoreValue == null || maxScoreValue <= 0 || score > maxScoreValue) {
                                _uiState.value = CreditState.Error()
                            } else {
                                getLocalScoreDetail(this).let {
                                    _uiState.value = CreditState.Success(it)
                                }
                            }
                        }

                    } else {
                        _uiState.value = CreditState.Error()
                    }
                }
        }


    fun getLocalScoreDetail(creditReportInfo: CreditReportInfo): LocalScoreDetail {
        val maxScore = creditReportInfo.maxScoreValue
        val percentage =
            getPercentage(creditReportInfo.score ?: 0, maxScore ?: 0)
        val color = getColorByPercentage(percentage)
        val score = creditReportInfo.score
        val angle = getAngle(percentage.toFloat())

        return LocalScoreDetail(
            score = score.toString(),
            angle = angle,
            color = color,
            maxScore.toString()
        );
    }

    fun getAngle(percentage: Float): Int = (360 * (percentage / 100)).toInt()

    fun getColorByPercentage(percentage: Int): Int {
        return when {
            percentage < 55 -> {
                Color.RED
            }
            percentage in 55..69 -> {
                Color.YELLOW
            }
            else -> {
                Color.GREEN
            }
        }
    }

    fun getPercentage(currentScore: Int, maxScoreValue: Int): Int {
        var result = 0
        if (currentScore > maxScoreValue) {
            return result
        }

        result = (currentScore * 100) / maxScoreValue

        if (result < 0) {
            result = 0
        }

        return result
    }
}