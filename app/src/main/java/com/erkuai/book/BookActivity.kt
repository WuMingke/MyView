package com.erkuai.book

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.erkuai.book.chapter3.FallingBallEvaluator
import com.erkuai.book.chapter3.FallingBallImageView
import com.erkuai.book.chapter3.MyEvaluator
import com.erkuai.book.chapter3.MyInterpolator
import com.erkuai.myview.R

class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_book)

//        setContentView(R.layout.value_animator)
//        val tv = findViewById<Button>(R.id.tv).apply {
//            setOnClickListener {
//                testValueAnimator(it)
//            }
//        }

        setContentView(R.layout.object_animator)
        findViewById<FallingBallImageView>(R.id.iv).apply {
            setOnClickListener {
                testObjectAnimator(it)
            }
        }
    }

    private fun testObjectAnimator(view: View) {
        val animator = ObjectAnimator.ofObject(
            view, "fallingPos", FallingBallEvaluator(), Point(0, 0), Point(500, 500)
        )
        animator.duration = 2000
        animator.start()
    }


    private fun testValueAnimator(view: View) {
        val animator = ValueAnimator.ofInt(0, 400)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            view.layout(curValue, curValue, curValue + view.width, curValue + view.height)
        }
        animator.duration = 1000
        animator.interpolator = MyInterpolator()
        animator.setEvaluator(MyEvaluator())
        animator.start()
    }
}