package com.erkuai.book.chapter10

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.util.AttributeSet
import android.view.View

class ShapeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.GRAY
        style = Paint.Style.STROKE
    }

    //    private val shape = RectShape()
//    private val shape = OvalShape()
//    private val shape = ArcShape(0f,180f)
        private val shape = OvalShape()
    private val shapeDrawable = ShapeDrawable(shape)

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
//        shapeDrawable.bounds = Rect(50, 50, 200, 100)
        shapeDrawable.paint.color = Color.YELLOW
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        shapeDrawable.bounds = Rect(0, 0, w, h)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        shapeDrawable.draw(canvas)
    }
}