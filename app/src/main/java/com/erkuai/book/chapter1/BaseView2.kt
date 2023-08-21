package com.erkuai.book.chapter1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class BaseView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 50f
        color = Color.RED
        style = Paint.Style.STROKE
    }

    private val mPath = Path()

    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 5f
        color = Color.RED
        style = Paint.Style.FILL
        textAlign = Paint.Align.LEFT
        textSize = 80f
//        scaleX = 2f
//        scaleY = 2f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val rect1 = RectF(50f, 50f, 200f, 300f)
        mPath.addRoundRect(rect1, 10f, 15f, Path.Direction.CCW)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(mPath, mPaint)

        canvas.save()
        canvas.translate(0f, 400f)


        canvas.drawText("床前明月光", 200f, 0f, mTextPaint)

        canvas.restore()
    }
}