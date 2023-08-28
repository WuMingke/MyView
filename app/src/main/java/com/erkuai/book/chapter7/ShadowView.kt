package com.erkuai.book.chapter7

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.erkuai.getAvatar

class ShadowView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.GRAY
        style = Paint.Style.STROKE
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private val bitmap = getAvatar(resources, 400)
    private val filter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        mPaint.setShadowLayer(50f, 50f, 50f, Color.GRAY)
        val alphaBitmap = bitmap.extractAlpha()
        mPaint.setMaskFilter(filter)
        canvas.drawBitmap(alphaBitmap, 10f, 10f, mPaint)

        mPaint.setMaskFilter(null)
        canvas.drawBitmap(bitmap, 0f, 0f, mPaint)
    }
}