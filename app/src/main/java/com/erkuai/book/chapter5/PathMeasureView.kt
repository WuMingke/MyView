package com.erkuai.book.chapter5

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.view.ContentInfoCompat.Flags
import com.erkuai.getAvatar
import java.nio.file.PathMatcher
import kotlin.math.PI
import kotlin.math.atan2

class PathMeasureView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.RED
        style = Paint.Style.STROKE
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val path = Path()
//        path.lineTo(0f, 0f)
//        path.lineTo(0f, 100f)
//        path.lineTo(100f, 100f)
//        path.lineTo(100f, 0f)
//
//        val measure1 = PathMeasure(path, false)
//        val measure2 = PathMeasure(path, true)
//        Log.i("wmkwmk", "measure1-->${measure1.length}, measure2-->${measure2.length}")
//
//        canvas.drawPath(path, mPaint)


//        canvas.translate(100f, 100f)
//        path.addRect(-50f, -50f, 50f, 50f, Path.Direction.CW)
//        val dst = Path()
//        val pathMeasure = PathMeasure(path, false)
//        pathMeasure.getSegment(0f, 150f, dst, false)
//        canvas.drawPath(dst, mPaint)

        val length = pathMeasure?.length!! * currValue
        mDstPath.reset()
        pathMeasure?.getSegment(0f, length, mDstPath, true)
        canvas.drawPath(mDstPath, mPaint)

        pathMeasure?.getPosTan(length, pos, tan)
        val degrees = Math.toDegrees(atan2(tan[1].toDouble(), tan[0].toDouble()))
        matrix.reset()
        matrix.postRotate(degrees.toFloat(), bitmap.width / 2f, bitmap.height / 2f)
        matrix.postTranslate(pos[0] - bitmap.width / 2f, pos[1] - bitmap.height / 2f)
        canvas.drawBitmap(bitmap, matrix, mPaint)


        pathMeasure?.nextContour()

    }

    private val mDstPath = Path()
    private val circlePath = Path()
    private var currValue = 0f
    private var pathMeasure: PathMeasure? = null

    private val bitmap = getAvatar(resources, 20)
    private val pos = FloatArray(2)
    private val tan = FloatArray(2)
    private val matrix = Matrix()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        circlePath.addCircle(100f, 100f, 50f, Path.Direction.CW)
        pathMeasure = PathMeasure(circlePath, true)

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener {
            currValue = it.animatedValue as Float
            invalidate()
        }
        animator.interpolator = AccelerateInterpolator()
        animator.duration = 5000
        animator.start()

    }
}