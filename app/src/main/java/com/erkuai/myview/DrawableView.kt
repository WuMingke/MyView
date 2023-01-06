package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View

/**
 * Drawable 的绘制
 */
class DrawableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    //    private val drawable = ColorDrawable(Color.RED)
    private val drawable = MeshDrawable()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawable.setBounds(0, 0, width, height) // drawable 的绘制需要设置范围，这是必须的
        drawable.draw(canvas)

//        canvas.drawBitmap()
    }
}