package com.erkuai.myview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.erkuai.dp

class MaterialEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val mTextSize = 12.dp
    private val mTextMargin = 8.dp
    private val mHorizontalOffset = 5.dp
    private val mVerticalOffset = 23.dp
    private var floatingLabelShown = false
    private var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var animator1: ObjectAnimator? = null

    //    private var animator2: ObjectAnimator? = null
    private var mExtraVerticalOffset = 16.dp

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = mTextSize
    }

    private var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (value) {
                    setPadding(paddingLeft, paddingTop + mTextSize.toInt() + mTextMargin.toInt(), paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, paddingTop - mTextSize.toInt() - mTextMargin.toInt(), paddingRight, paddingBottom)
                }
            }
        }

    init {

        /**
         * attrs：这个控件在布局文件里声明的所有属性
         * obtainStyledAttributes：过滤除了R.styleable.MaterialEditText声明的所有属性，也就是保留R.styleable.MaterialEditText声明的属性
         * typedArray.getXXX：通过id（0，1，2。。。。）获取属性值
         * R.styleable.MaterialEditText_useFloatingLabel 编译后就是 0
         * R.styleable.MaterialEditText 声明的时候，排在第几个，index就是几
         *
         * 所以以下代码等价于：
         * val typedArray1 = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
         * typedArray1.getBoolean(0,true)
         */
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)


        typedArray.recycle()

//        if (useFloatingLabel) {
//            setPadding(paddingLeft, paddingTop + mTextSize.toInt() + mTextMargin.toInt(), paddingRight, paddingBottom)
//        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        animator1 = ObjectAnimator.ofFloat(this, "floatingLabelFraction", 1f, 0f)
//        animator2 = ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f) // 可以调用reverse替代
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if (useFloatingLabel && floatingLabelShown && text.isNullOrEmpty()) {
            floatingLabelShown = false
            animator1?.start()
        } else if (useFloatingLabel && !floatingLabelShown && !text.isNullOrEmpty()) {
            floatingLabelShown = true
//            animator2?.start()
            animator1?.reverse()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVertical = mVerticalOffset + mExtraVerticalOffset * (1 - floatingLabelFraction)
        canvas.drawText(hint?.toString() ?: "", mHorizontalOffset, currentVertical, mPaint)

    }

}