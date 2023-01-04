package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * 裁切
 */
class TestView9(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val padding = 100.dp
    private val bitmapSize = 200.dp
    private val bitmap = getAvatar(resources, bitmapSize.toInt())
    private val path = Path().apply {
        addOval(padding, padding, padding + bitmapSize, padding + bitmapSize, Path.Direction.CCW)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        canvas.clipRect(padding, padding, padding + bitmapSize / 2, padding + bitmapSize / 2)

        canvas.clipPath(path) // 有锯齿，

        canvas.drawBitmap(bitmap, padding, padding, mPaint)

    }
}