package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.erkuai.dp
import com.erkuai.getAvatar

/**
 * 圆形头像
 */
class TestView4(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mWidth = 100.dp
    private val mMargin = 20.dp
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val rectf = RectF(mMargin, mMargin, mWidth + mMargin, mWidth + mMargin)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layer = canvas.saveLayer(rectf, null) // 离屏缓冲
        // destination
        canvas.drawOval(rectf, paint)

        paint.xfermode = xfermode

        // source
        canvas.drawBitmap(getAvatar(resources, mWidth.toInt()), mMargin, mMargin, paint)

        paint.xfermode = null
        canvas.restoreToCount(layer)

    }
}