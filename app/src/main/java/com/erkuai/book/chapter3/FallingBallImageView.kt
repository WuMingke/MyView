package com.erkuai.book.chapter3

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class FallingBallImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    fun setFallingPos(pos: Point) {
        layout(pos.x, pos.y, pos.x + width, pos.y + height)
    }
}

class FallingBallEvaluator : TypeEvaluator<Point> {

    private val mPoint = Point()
    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        mPoint.x = startValue.x + (fraction * (endValue.x - startValue.x)).toInt()
        if (fraction * 2 <= 1) {
            mPoint.y = startValue.y + (fraction * 2 * (endValue.y - startValue.y)).toInt()
        } else {
            mPoint.y = endValue.y
        }
        return mPoint
    }

}