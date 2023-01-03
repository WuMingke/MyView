package com.erkuai.myview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 测试 Xfermode
 */
class TestView5(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 官网的例子，它其实不仅仅是一个圆和一个方，而是携带有圆和方的两张矩形图
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val rectf = RectF(150.dp, 50.dp, 300.dp, 200.dp)

    private val circleBitmap = Bitmap.createBitmap(150.dp.toInt(), 150.dp.toInt(), Bitmap.Config.ARGB_8888)
    private val squareBitmap = Bitmap.createBitmap(150.dp.toInt(), 150.dp.toInt(), Bitmap.Config.ARGB_8888)

    init {
        val canvas = Canvas(circleBitmap)
        paint.color = Color.RED
        canvas.drawOval(50.dp, 0.dp, 150.dp, 100.dp, paint)
        canvas.setBitmap(squareBitmap)
        paint.color = Color.GREEN
        canvas.drawRect(0.dp, 50.dp, 100.dp, 150.dp, paint)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layer = canvas.saveLayer(rectf, null)

        canvas.drawBitmap(circleBitmap, 150.dp, 50.dp, paint)

        paint.xfermode = xfermode

        canvas.drawBitmap(squareBitmap, 150.dp, 50.dp, paint)

        paint.xfermode = null
        canvas.restoreToCount(layer)
    }
}