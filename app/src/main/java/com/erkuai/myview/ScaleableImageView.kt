package com.erkuai.myview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.erkuai.dp
import com.erkuai.getAvatar
import kotlin.math.max
import kotlin.math.min

private val IMAGE_SIZE = 300.dp.toInt()
private const val EXTRA_SCALE_FACTOR = 1.5f

// 双向滑动
class ScaleableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),

    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable, ScaleGestureDetector.OnScaleGestureListener {

    private val bitmap = getAvatar(resources, IMAGE_SIZE)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private val gestureDetector = GestureDetectorCompat(context, this)
    private var big = false
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnimator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
    }

    private var offsetX = 0f
    private var offsetY = 0f
    private val overScroller = OverScroller(context)
    private val scaleGestureDetector = ScaleGestureDetector(context, this)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (width - IMAGE_SIZE) / 2f
        originalOffsetY = (height - IMAGE_SIZE) / 2f

        if (bitmap.width / bitmap.height.toFloat() > 0) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }

        currentScale = smallScale
        scaleAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
//        val scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var result = scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            result = gestureDetector.onTouchEvent(event)
        }
        return result
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(
        downEvent: MotionEvent?, currentEvent: MotionEvent?, distanceX: Float, distanceY: Float
    ): Boolean {
        if (big) {
            offsetX -= distanceX
            offsetY -= distanceY
            fixOffsets()
            invalidate()
        }
        return false
    }

    private fun fixOffsets() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onFling(
        downEvent: MotionEvent?, currentEvent: MotionEvent?, velocityX: Float, velocityY: Float
    ): Boolean {
        if (big) {
            val x = (bitmap.width * bigScale - width) / 2
            val y = (bitmap.height * bigScale - height) / 2
            overScroller.fling(
                offsetX.toInt(), offsetY.toInt(),
                velocityX.toInt(), velocityY.toInt(),
                -x.toInt(), x.toInt(),
                -y.toInt(), y.toInt(),
//                100.dp.toInt(), 100.dp.toInt()
            )
            ViewCompat.postOnAnimation(this, this)
        }
        return false
    }

    override fun run() {
        if (overScroller.computeScrollOffset()) {
            offsetX = overScroller.currX.toFloat()
            offsetY = overScroller.currY.toFloat()
            invalidate()

            ViewCompat.postOnAnimation(this, this)
        }
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        big = !big
        if (big) {
            offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
            fixOffsets()
            scaleAnimator.start()
        } else {
            scaleAnimator.reverse()
        }
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        val tempCurrentScale = currentScale * detector.scaleFactor
        return if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
            false
        } else {
            currentScale *= detector.scaleFactor
            true
        }

    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
        offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
    }


}