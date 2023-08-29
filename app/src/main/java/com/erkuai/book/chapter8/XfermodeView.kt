package com.erkuai.book.chapter8

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.erkuai.dp
import com.erkuai.getAvatar
import com.erkuai.getPic

class XfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.GRAY
        style = Paint.Style.STROKE
    }

    private val dst = getAvatar(resources, 200.dp.toInt()) // 与xml一样大
    private val src = getPic(resources, 200.dp.toInt())
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layer = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
        canvas.drawBitmap(dst, 0f, 0f, mPaint)
        mPaint.xfermode = xfermode
        canvas.drawBitmap(src, width / 2f, height / 2f, mPaint)
        mPaint.xfermode = null

        canvas.restoreToCount(layer)
    }
}