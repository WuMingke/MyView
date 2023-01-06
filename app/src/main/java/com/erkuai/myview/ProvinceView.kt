package com.erkuai.myview

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 测试 TypeEvaluator
 */
class ProvinceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100.dp
        textAlign = Paint.Align.CENTER
    }

    /*private*/ var province = "四川省"
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawText(province, width / 2f, height / 2f, mPaint)
    }
}

class ProvinceEvaluator : TypeEvaluator<String> {

    private val provinces = arrayListOf<String>().apply {
        add("四川省")
        add("上海市")
        add("广东省")
        add("广州市")
        add("深圳市")
        add("回家")
    }

    override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
        val startIndex = provinces.indexOf(startValue)
        val endIndex = provinces.indexOf(endValue)
        val currentIndex = startIndex + (endIndex - startIndex) * fraction
        return provinces[currentIndex.toInt()]
    }
}