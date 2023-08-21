package com.erkuai.myview

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.erkuai.dp
import com.erkuai.getAvatar

/**
 * camera的例子
 */
class TestView10(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val padding = 100.dp
    private val bitmapSize = 200.dp
    private val bitmap = getAvatar(resources, bitmapSize.toInt())

    private val camera = Camera().apply {
        rotateX(30f) // 旋转30度,默认轴心在左上角原点，只能移动图像到原点先做变换再移回去
        setLocation(0f, 0f, -8f * resources.displayMetrics.density) // 设置camera的位置,默认值是-8英寸
        // * resources.displayMetrics.density 让它与屏幕像素相关，那么各个屏幕表现就会差不多
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        camera.applyToCanvas(canvas) // camera的变换应用到canvas上

        val d = padding + bitmapSize / 2
        canvas.translate(d, d)
        camera.applyToCanvas(canvas)
        canvas.translate(-d, -d)    // 倒着写

        canvas.drawBitmap(bitmap, padding, padding, mPaint)

    }
}