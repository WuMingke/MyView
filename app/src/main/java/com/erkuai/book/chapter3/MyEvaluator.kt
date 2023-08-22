package com.erkuai.book.chapter3

import android.animation.TypeEvaluator

class MyEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return 200 + startValue + (fraction * (endValue - startValue)).toInt()
    }
}