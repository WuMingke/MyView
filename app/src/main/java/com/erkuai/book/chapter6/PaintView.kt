package com.erkuai.book.chapter6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.RED
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val baseLineX = 0f
        val baseLineY = 200f
        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, mPaint)

        mPaint.color = Color.GREEN
        mPaint.textSize = 120f // px
        canvas.drawText("mingke", baseLineX, baseLineY, mPaint)
    }
}