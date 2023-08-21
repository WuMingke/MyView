package com.erkuai.myview

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.erkuai.dp

/**
 * 测试 TypeEvaluator
 */
class PointFView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 20.dp
        strokeCap = Paint.Cap.ROUND
    }

    /*private*/ var point = PointF(0f, 0f) // 如果不是对象，可以使用 private ，很奇怪（原因是没有写get方法，导致动画取不到初始值！！）
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPoint(point.x, point.y, mPaint)
    }
}

class PointFEvaluator : TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF?, endValue: PointF?): PointF {

        val startX = startValue?.x ?: 0f
        val endX = endValue?.x ?: 0f
        val currentX = startX + (endX - startX) * fraction
        val startY = startValue?.y ?: 0f
        val endY = endValue?.y ?: 0f
        val currentY = startY + (endY - startY) * fraction
        return PointF(currentX, currentY)
    }
}