package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.text.Layout
import android.text.PrecomputedText
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * 多行绘制
 */
class TestView8(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val text = "lorem ipsum"
    private val text2 =
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
    private var staticLayout: StaticLayout? = null

    private val imageSize = 150.dp
    private val imagePadding = 70.dp
    private val bitmap = getAvatar(resources, imageSize.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontMetrics = FontMetrics()
    private val widths = floatArrayOf()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        if (staticLayout == null) {
//            staticLayout = StaticLayout(text2, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 1f, false)
//        }
//
//        staticLayout?.draw(canvas)

        canvas.drawBitmap(bitmap, width - imageSize, 0f + imagePadding, paint)

        paint.textSize = 16.dp
        paint.getFontMetrics(fontMetrics)
        var start = 0
        var count = 0
        var verticalOffset = 0 - fontMetrics.top
        while (start < text2.length) {
            val maxWith = if (verticalOffset + fontMetrics.bottom < imagePadding ||
                verticalOffset + fontMetrics.top > imagePadding + imageSize
            ) {
                width.toFloat()
            } else {
                width.toFloat() - imageSize
            }
            count = paint.breakText(text2, start, text2.length, true, maxWith, widths)
            canvas.drawText(text2, start, start + count, 0f, verticalOffset, paint)
            start += count
            verticalOffset += paint.fontSpacing
        }
    }
}