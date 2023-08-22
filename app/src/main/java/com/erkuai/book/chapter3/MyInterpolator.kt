package com.erkuai.book.chapter3

import android.animation.TimeInterpolator

class MyInterpolator : TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        return 1 - input
    }
}