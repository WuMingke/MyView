package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View

class TestView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRADIUS = 100.dp
    private val path = Path()
    private val pathMeasure = PathMeasure()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path.addCircle(width / 2f, height / 2f, mRADIUS, Path.Direction.CW)

        path.addRect(width / 2f - mRADIUS, height / 2f, width / 2 + mRADIUS, height / 2 + 2 * mRADIUS, Path.Direction.CCW)

//        path.fillType = Path.FillType.EVEN_ODD
        pathMeasure.setPath(path, true)

//        pathMeasure.length

//        pathMeasure.getPosTan()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        canvas.drawLine(100f, 100f, 200f, 200f, paint)

//        canvas.drawCircle(width / 2f, height / 2f, mRADIUS, paint)

        canvas.drawPath(path, paint)
    }

}