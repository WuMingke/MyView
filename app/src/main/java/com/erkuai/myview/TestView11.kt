package com.erkuai.myview

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 翻页效果
 */
class TestView11(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val padding = 100.dp
    private val bitmapSize = 200.dp
    private val bitmap = getAvatar(resources, bitmapSize.toInt())

    private val camera = Camera().apply {
        rotateX(50f)
        setLocation(0f, 0f, -8f * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val d = padding + bitmapSize / 2

//        canvas.save()
//        // 上半部分
//        canvas.translate(d, d)
//        canvas.clipRect(-bitmapSize / 2, -bitmapSize / 2, bitmapSize / 2, 0f)
//        canvas.translate(-d, -d)
//        canvas.drawBitmap(bitmap, padding, padding, mPaint)
//
//        canvas.restore()
//
//        canvas.save()
//        // 下半部分
//        canvas.translate(d, d)
//        camera.applyToCanvas(canvas)
//        canvas.clipRect(-bitmapSize / 2, 0f, bitmapSize / 2, bitmapSize / 2)
//        canvas.translate(-d, -d)
//
//        canvas.drawBitmap(bitmap, padding, padding, mPaint)
//
//        canvas.restore()

        /*********** 斜的 ************/
        canvas.save()
        // 上半部分
        canvas.translate(d, d)
        canvas.rotate(-30f)
        canvas.clipRect(-bitmapSize, -bitmapSize, bitmapSize, 0f)
        canvas.rotate(30f)
        canvas.translate(-d, -d)
        canvas.drawBitmap(bitmap, padding, padding, mPaint)

        canvas.restore()

        canvas.save()
        // 下半部分
        canvas.translate(d, d)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-bitmapSize, 0f, bitmapSize, bitmapSize)
        canvas.rotate(30f)
        canvas.translate(-d, -d)

        canvas.drawBitmap(bitmap, padding, padding, mPaint)

        canvas.restore()

    }

}