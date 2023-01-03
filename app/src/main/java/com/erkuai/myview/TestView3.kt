package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图
 */
class TestView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val angles = floatArrayOf(60f, 90f, 150f, 60f)
    private val colors = intArrayOf(Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW)
    private var startAngle = 0f
    private val offsetLength = 10.dp
    private val offsetIndex = 2

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        startAngle = 0f
        for (index in angles.indices) {
            paint.color = colors[index]
            val sweep = angles[index]

            if (index == offsetIndex) {
                canvas.save()
                val angle = startAngle + sweep / 2f
                val offsetX = offsetLength * cos(Math.toRadians(angle.toDouble())).toFloat()
                val offsetY = offsetLength * sin(Math.toRadians(angle.toDouble())).toFloat()
                canvas.translate(offsetX, offsetY)
            }
            canvas.drawArc(
                width / 2f - 150.dp, height / 2f - 150.dp,
                width / 2f + 150.dp, height / 2f + 150.dp,
                startAngle, sweep, true, paint
            )
            if (index == offsetIndex) {
                canvas.restore()
            }

            startAngle += sweep
        }
    }
}