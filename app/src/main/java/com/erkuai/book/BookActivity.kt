package com.erkuai.book

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.erkuai.myview.R

class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_book)
        setContentView(R.layout.value_animator)

        val tv = findViewById<Button>(R.id.tv).apply {
            setOnClickListener {
                testValueAnimator(it)
            }
        }
    }


    // TODO: 2023/8/22 mingKE 测试
    private fun testValueAnimator(view: View) {
        val animator = ValueAnimator.ofInt(0, 400)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            view.layout(curValue, 0, curValue + view.width, 0)
        }
        animator.start()
    }
}