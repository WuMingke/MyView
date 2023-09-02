package com.erkuai.book.chapter10

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class CustomViewGroup(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        MeasureSpec.UNSPECIFIED // 子元素可以得到任意想要的大小
        MeasureSpec.EXACTLY // match_parent 、 具体值
        MeasureSpec.AT_MOST // 至多 wrap_content

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec) // 测量的宽
        // 如果是模式是用户设置的，那么不应该取修改，如果是wrap_content，那么就需要控件去计算大小
        val width = if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth
        } else {
            10 // 计算的宽
        }
        setMeasuredDimension(width, width)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return super.generateLayoutParams(attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return super.generateDefaultLayoutParams()
    }
}