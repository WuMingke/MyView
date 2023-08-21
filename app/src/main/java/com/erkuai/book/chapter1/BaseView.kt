package com.erkuai.book.chapter1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.provider.CalendarContract.Colors
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.erkuai.dp

class BaseView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 50f
        color = Color.RED
        style = Paint.Style.STROKE
    }


    private val mRect = RectF(100f, 100f, 800f, 800f)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        Log.i("wmkwmk", "onSizeChanged: ${mRect.contains(100f, 150f)}")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        canvas.translate(100f, 0f)

//        canvas.drawCircle(200.dp, 200.dp, 150.dp, mPaint)
//
//        canvas.translate(100f, 0f)
//
//        mPaint.color = Color.GREEN
//        canvas.drawCircle(200.dp, 200.dp, 150.dp, mPaint)


        canvas.drawColor(Color.RED)
        canvas.save()

        canvas.clipRect(mRect)
        if (mRect.contains(mX, mY)) {
            canvas.drawColor(Color.GREEN)
        } else {
            canvas.drawColor(Color.GRAY)
        }


        canvas.restore()

//        canvas.drawColor(Color.BLUE)

    }

    private var mX = 0f
    private var mY = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_DOWN) {
            mX = event.x
            mY = event.y
            if (mRect.contains(mX, mY)) {
                invalidate()
//                return true
            }
        } else if (event?.actionMasked == MotionEvent.ACTION_UP) {
            mX = -1f
            mY = -1f
//            postInvalidate()
            invalidate()
        }
        return true
    }
}