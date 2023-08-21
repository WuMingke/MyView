package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.erkuai.dp

/**
 * 文字居中
 */
class TestView6(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleColor = Color.parseColor("#90A4AE")
    private val highlightColor = Color.parseColor("#FF4081")
    private val ringWidth = 20.dp
    private val radius = 150.dp
    private val bounds = Rect()
    private val fontMetrics = FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.color = circleColor
        paint.strokeWidth = ringWidth
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)


        paint.color = highlightColor
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - radius, height / 2f - radius,
            width / 2f + radius, height / 2f + radius,
            -90f, 225f,
            false, paint
        )

        // 文字
        paint.style = Paint.Style.FILL
        paint.textSize = 100.dp
        paint.typeface = Typeface.SANS_SERIF
//        paint.isFakeBoldText = true // "假的粗体"
        paint.textAlign = Paint.Align.CENTER

        val s = "aaa"
//        paint.getTextBounds(s, 0, s.length, bounds) // 紧贴着文本的Bound,保存相距起始点的偏移
//        val d = (bounds.top + bounds.bottom) / 2f// 这种方式只适合静态的状态，
//        canvas.drawText(s, width / 2f, height / 2f - d, paint)
        // 如果 文本是动态的，那么它的上下边是会变化的，那么展示出来就是跳动的

        paint.getFontMetrics(fontMetrics)
        val d = (fontMetrics.ascent + fontMetrics.descent) / 2 // 适合动态文字，这样写就不会跳动，相距baseline的偏移
        canvas.drawText(s, width / 2f, height / 2f - d, paint)

        /**
         * 文本有5条线定位
         * top
         * ascent    // 文字主体的头部
         * baseline
         * descent   // 文字主体的底部
         * bottom
         */

    }

}