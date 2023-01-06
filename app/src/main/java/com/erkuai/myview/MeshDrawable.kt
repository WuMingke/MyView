package com.erkuai.myview

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt

/**
 * Drawable的自定义
 */
class MeshDrawable : Drawable() {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#F9A825".toColorInt()
        strokeWidth = 5.dp
    }
    private var interval = 50.dp

    override fun draw(canvas: Canvas) {
        var x = bounds.left.toFloat()
        while (x <= bounds.right) {
            canvas.drawLine(
                x, bounds.top.toFloat(),
                x, bounds.bottom.toFloat(),
                mPaint
            )
            x += interval
        }

        var y = bounds.top.toFloat()
        while (y <= bounds.bottom) {
            canvas.drawLine(
                bounds.left.toFloat(), y,
                bounds.right.toFloat(), y,
                mPaint
            )
            y += interval
        }


    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getAlpha(): Int {
        return mPaint.alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getColorFilter(): ColorFilter? {
        return mPaint.colorFilter
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int {
        return when (mPaint.alpha) {
            0 -> PixelFormat.TRANSPARENT
            0xFF -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSLUCENT
        }
    }
}