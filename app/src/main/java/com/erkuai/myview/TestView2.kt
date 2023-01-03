package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘
 */
class TestView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3.dp
    }
    private val mLength = 100f.dp
    private val mOPEN_ANGLE = 120f
    private val mDASH_WIDTH = 2.dp
    private val mDASH_HEIGHT = 10.dp
    private val dash = Path().apply {
        addRect(0f, 0f, mDASH_WIDTH, mDASH_HEIGHT, Path.Direction.CCW)
    }

    // "用特效的方式画图"
    private var pathEffect: PathDashPathEffect? = null

    private val pathMeasure = PathMeasure()
    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path.reset()
        path.addArc(
            width / 2f - 150.dp, height / 2f - 150.dp,
            width / 2f + 150.dp, height / 2f + 150.dp,
            90 + mOPEN_ANGLE / 2f, 360 - mOPEN_ANGLE
        )

        pathMeasure.setPath(path, false)

        val length = pathMeasure.length

        // 20个刻度，20个间隔
        val fl = (length - mDASH_WIDTH) / 20f

        pathEffect = PathDashPathEffect(dash, fl, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        //
        val index = 2
        val fl = (360 - mOPEN_ANGLE) / 20.0 * index
        val x1 = Math.toRadians(fl + 90 + mOPEN_ANGLE / 2) // 角度转弧度

        val x = width / 2f + cos(x1) * mLength
        val y = height / 2f + sin(x1) * mLength
        canvas.drawLine(width / 2f, height / 2f, x.toFloat(), y.toFloat(), paint)
    }
}