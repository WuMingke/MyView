package com.erkuai.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.children
import com.erkuai.dp
import java.util.Random
import kotlin.math.max

/**
 * 自定义布局 的类型
 * 1、继承已有的View，简单改写它们的尺寸：重写onMeasure()
 *      eg：SquareImageView 方形的
 * 2、对自定义View完全进行自定义尺寸计算：重写onMeasure()
 *      eg：CircleView
 * 3、自定义Layout：重写onMeasure()、onLayout()
 *      eg：TagLayout
 */
class TestView15(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
}

/**
 * 第一种 步骤
 *  重写onMeasure()
 *  用getMeasuredWidth()和getMeasuredHeight()获取到测量出的尺寸
 *  计算出最终要的尺寸
 *  用setMeasuredDimension(width,height)把结果保存
 */
class SquareImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = measuredWidth
        val measuredHeight = measuredHeight
        val size = if (measuredWidth < measuredHeight) {
            measuredWidth
        } else {
            measuredHeight
        }
        // 以上可以用kotlin简写: val size = min(measuredWidth,measuredHeight)
        setMeasuredDimension(size, size)
    }

    /**
     * getMeasuredWidth() getMeasuredHeight()   获取到的是期望尺寸
     * getWidth() getHeight()   获取到的是实际尺寸
     *
     * 在测量过程中用第一类，在其它时候用第二类
     */
}

/**
 * 第二种 步骤
 *  重写onMeasure()
 *  计算出自己的尺寸
 *  用resolveSize()或者resolveSizeAndState()修正结果
 *  用setMeasuredDimension(width,height)把结果保存
 */
class CircleView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRadius = 100.dp
    private val mPadding = 100.dp

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec) // 父View的测量 就不要了
        val size = ((mPadding + mRadius) * 2).toInt()

        val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val width = when (specWidthMode) {
            MeasureSpec.AT_MOST -> { // wrap_content
                if (size > specWidthSize) { // 我自己的>测量出的
                    specWidthSize
                } else {
                    size
                }
            }
            MeasureSpec.EXACTLY -> { // match_parent 和 确定的值
                specWidthSize
            }
            MeasureSpec.UNSPECIFIED -> { // "200dp"
                size
            }
            else -> {
                size
            }
        }

        //   以上代码可以使用 resolveSize() 代替

        setMeasuredDimension(width, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(mPadding + mRadius, mPadding + mRadius, mRadius, mPaint)
    }
}

/**
 * 第三种 步骤
 *  重写onMeasure()
 *      遍历每个子View，测量子View
 *          测量完成后，得出子View的实际位置和尺寸，并暂时保存
 *          有些子View可能需要重新测量
 *      测量出所有子View的位置和尺寸后，计算出自己的尺寸，并用setMeasuredDimension(width,height)把结果保存
 *  重写onLayout()
 *      遍历每个子View，调用它们的layout()方法来将位置和尺寸传给它们
 *
 */
class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val childrenBounds = mutableListOf<Rect>() // 只需要一行的数据就行了

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec) // 父View给的宽高限制
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var widthUsed = 0
        var heightUsed = 0
        var lineMaxHeight = 0
        var lineWidthUsed = 0 // 最新一行的宽度

        for ((index, child) in children.withIndex()) {
//            val lp = child.layoutParams // xml中的属性
//            var childWidthMode = 0
//            var childWidthSize = 0
//            when (lp.width) {
//                LayoutParams.MATCH_PARENT -> { // 开发者的条件
//                    when (widthMode) { // 父View的条件
//                        MeasureSpec.AT_MOST,
//                        MeasureSpec.EXACTLY -> {
//                            childWidthMode = MeasureSpec.EXACTLY
//                            childWidthSize = widthSize - withUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthMode = MeasureSpec.UNSPECIFIED
//                            childWidthSize = 0
//                        }
//                    }
//                }
//                LayoutParams.WRAP_CONTENT -> { // 开发者的条件
//                    when (widthMode) { // 父View的条件
//                        MeasureSpec.AT_MOST,
//                        MeasureSpec.EXACTLY -> {
//                            childWidthMode = MeasureSpec.AT_MOST
//                            childWidthSize = widthSize - withUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthMode = MeasureSpec.UNSPECIFIED
//                            childWidthSize = 0
//                        }
//                    }
//                }
//                else -> {
//                    childWidthMode = MeasureSpec.EXACTLY
//                    childWidthSize = layoutParams.width
//                }
//            }

//            child.measure() // 父View会给子View的要求,TagLayout给子View的要求，子View就会调用onMeasure

            // 以上代码可替换为
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if ((widthUsed + child.measuredWidth > widthSize) && widthMode != MeasureSpec.UNSPECIFIED) {
                // 折行
                lineWidthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                // 需要再测量一次
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect()) // 这里是已知的，只会有一行的数据，所以可以new 对象
            }
            val childBounds = childrenBounds[index]
            childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
//            heightUsed += child.measuredHeight

            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
        }

        // ??? 这里没有处理 margin
        val selfWidth = widthUsed
        val selfHeight = lineMaxHeight + heightUsed
        setMeasuredDimension(selfWidth, selfHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) { // 这些数据是指TagLayout在父View中的值
        for ((index, child) in children.withIndex()) {
//            child.layout(0, 0, r - l, b - t) // 让子View撑满TagLayout，不能直接用 l t r b

            val childBounds = childrenBounds[index]
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
        }
    }

    // 因为 measureChildWithMargins 方法里面有强转
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

}

class ColoredTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRandom = Random()
    private val mColors = arrayListOf<Int>().apply {
        add(Color.parseColor("#E91E63"))
        add(Color.parseColor("#673AB7"))
        add(Color.parseColor("#2196F3"))
        add(Color.parseColor("#795548"))
    }
    private val mTextSize = intArrayOf(16, 22, 28)
    private val mCornerRadius = 4.dp
    private val mXPadding = 16.dp.toInt()
    private val mYPadding = 8.dp.toInt()

    init {
        setTextColor(Color.WHITE)
        textSize = mTextSize[mRandom.nextInt(mTextSize.size)].toFloat()
        mPaint.color = mColors[mRandom.nextInt(mColors.size)]
        setPadding(mXPadding, mYPadding, mXPadding, mYPadding)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), mCornerRadius, mCornerRadius, mPaint)
        super.onDraw(canvas)
    }

}



























