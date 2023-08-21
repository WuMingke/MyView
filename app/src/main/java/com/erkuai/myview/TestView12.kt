package com.erkuai.myview

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.erkuai.dp
import com.erkuai.getAvatar

/**
 * 属性动画
 */
class TestView12(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val padding = 100.dp
    private val bitmapSize = 200.dp
    private val bitmap = getAvatar(resources, bitmapSize.toInt())

    private var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val camera = Camera().apply {
//        rotateX(bottomFlip)
        setLocation(0f, 0f, -8f * resources.displayMetrics.density)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val d = padding + bitmapSize / 2

//        canvas.save()
        canvas.withSave { // 代替save、restore
            // 上半部分
            canvas.translate(d, d)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-bitmapSize, -bitmapSize, bitmapSize, 0f)
            canvas.rotate(flipRotation)
            canvas.translate(-d, -d)
            canvas.drawBitmap(bitmap, padding, padding, mPaint)
        }
//        canvas.restore()

        canvas.save()
        // 下半部分
        canvas.translate(d, d)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(bottomFlip) // camera 因为这句，也需要save()
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-bitmapSize, 0f, bitmapSize, bitmapSize)
        canvas.rotate(flipRotation)
        canvas.translate(-d, -d)

        canvas.drawBitmap(bitmap, padding, padding, mPaint)

        canvas.restore()

    }

}