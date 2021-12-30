package com.kst.creditscoreapp.donut

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.kst.creditscoreapp.R

class MyDonutView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    FrameLayout(context, attributeSet, defStyle) {
    private val START_ANGLE_POINT = 270

    var scoreTextView: AppCompatTextView
    var desTextview: AppCompatTextView
    var relativeLayout: RelativeLayout

    lateinit var paint: Paint
    lateinit var rect: RectF
    var angle = 0f
    private val strokeWidth = 10
    private var left = 0f
    private var top = 0f
    var right = 0f
    private var bottom = 0f

    init {
        inflate(context, R.layout.my_dount_view, this)
        scoreTextView = findViewById(R.id.textview_title_2)
        desTextview = findViewById(R.id.textview_title_3)
        relativeLayout = findViewById(R.id.circleView)
        setupUi()
        setWillNotDraw(false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        left = (relativeLayout.left + 30).toFloat()
        top = (relativeLayout.top + 30).toFloat()
        right = (relativeLayout.right - 30).toFloat()
        bottom = (relativeLayout.bottom - 30).toFloat()
        rect = RectF(left, top, right, bottom)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        canvas.drawArc(rect, START_ANGLE_POINT.toFloat(), angle, false, paint)
    }

    private fun setupUi() {
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth.toFloat()
        paint.color = Color.YELLOW
    }

    fun setUpScoreViewDetail(score: String, @ColorInt color: Int, maxScore: String) {
        setPaintColor(color)
        setScoreDetail(score, color, maxScore)
    }

    private fun setPaintColor(color: Int) {
        paint.color = color
    }

    private fun setScoreDetail(score: String, color: Int, maxScore: String) {
        val strMeatFormat = resources.getString(R.string.donut_view_title2, maxScore)
        desTextview.text = strMeatFormat
        scoreTextView.text = score
        scoreTextView.setTextColor(color)
    }
}