package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class ShineTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private var mLinearGradient: LinearGradient? = null
    private var mGradientMatrix: Matrix? = null
    private var mViewWidth = 0f
    private var mTranslate = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mViewWidth == 0f) {
            mViewWidth = measuredWidth.toFloat()
            if (mViewWidth > 0) {
                val paint = paint
                mLinearGradient = LinearGradient(
                    0f, 0f, mViewWidth, 0f, intArrayOf(
                        currentTextColor, Color.WHITE, currentTextColor
                    ), null, Shader.TileMode.CLAMP
                )
                paint.shader = mLinearGradient
                mGradientMatrix = Matrix()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mGradientMatrix != null && mLinearGradient != null) {
            mTranslate += mViewWidth / 5
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = 0f
            }
            mGradientMatrix!!.setTranslate(mTranslate, 0f)
            mLinearGradient!!.setLocalMatrix(mGradientMatrix)

            //每60毫秒执行onDraw()
            postInvalidateDelayed(60)
        }
    }
}