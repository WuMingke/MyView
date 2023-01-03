package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.util.AttributeSet
import android.view.View

/**
 * 文字贴边
 */
class TestView7(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val s = "aaa"
    private val fontMetrics = Paint.FontMetrics()
    private val bounds = Rect()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
// 顶部
//        paint.textSize = 20.dp
//        paint.typeface = Typeface.SANS_SERIF
//        paint.textAlign = Paint.Align.LEFT
//        paint.getFontMetrics(fontMetrics)
//        val d = fontMetrics.top
//        canvas.drawText(s, 0f, 0f - d, paint)

        // 左部
        paint.textSize = 100.dp
        paint.getTextBounds(s, 0, s.length, bounds)
        canvas.drawText(s, 0f - bounds.left, 0f - bounds.top, paint)

    }
}