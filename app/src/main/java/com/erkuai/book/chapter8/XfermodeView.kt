package com.erkuai.book.chapter8

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import com.erkuai.getAvatar

class XfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.GRAY
        style = Paint.Style.STROKE
    }

    private val bitmap = getAvatar(resources, 400)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        mPaint.setXfermode(PorterDuffXfermode)

    }
}