package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 测试 ObjectAnimator
 */
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#007968")
    }

    private var radius = 50.dp
        set(value) {
            field = value
            invalidate()  // 自定义属性动画，需要在属性的set方法里调用invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(width / 2f, height / 2f, radius, mPaint)

    }
}