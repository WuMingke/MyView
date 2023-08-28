package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.erkuai.dp
import com.erkuai.getAvatar

// 多点触控1
class MultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val bitmap = getAvatar(resources, 300.dp.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            downX = event.x
            downY = event.y
            originalOffsetX = offsetX
            originalOffsetY = offsetY
        }
        if (event.actionMasked == MotionEvent.ACTION_MOVE) {
            offsetX = event.x - downX + originalOffsetX
            offsetY = event.y - downY + originalOffsetY
            invalidate()
        }
        return true
    }
}