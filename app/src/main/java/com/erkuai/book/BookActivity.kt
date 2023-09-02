package com.erkuai.book

import android.animation.Keyframe
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.erkuai.book.chapter3.FallingBallEvaluator
import com.erkuai.book.chapter3.FallingBallImageView
import com.erkuai.book.chapter3.MyEvaluator
import com.erkuai.book.chapter3.MyInterpolator
import com.erkuai.myview.R

class BookActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_book)

//        setContentView(R.layout.value_animator)
//        val tv = findViewById<Button>(R.id.tv).apply {
//            setOnClickListener {
//                testValueAnimator(it)
//            }
//        }

//        setContentView(R.layout.object_animator)
//        findViewById<FallingBallImageView>(R.id.iv).apply {
//            setOnClickListener {
//                testObjectAnimator(it)
//            }
//        }

//        setContentView(R.layout.view_group_item_animation)
//        val container = findViewById<LinearLayout>(R.id.container)
//        var i = 0
//        findViewById<Button>(R.id.add).setOnClickListener {
//            i++
//            val btn = Button(this)
//            btn.text = "btn $i"
//            container.addView(btn)
//        }
//        findViewById<Button>(R.id.remove).setOnClickListener {
//            if (i > 0) {
//                container.removeViewAt(0)
//            }
//            i--
//        }

//        val container2 = findViewById<LinearLayout>(R.id.container2)
//        val layoutTransition = LayoutTransition()
//        val animatorIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f)
//        layoutTransition.setAnimator(LayoutTransition.APPEARING, animatorIn)
//        val animatorOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f)
//        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animatorOut)
//        container2.layoutTransition = layoutTransition
//        var i = 0
//        findViewById<Button>(R.id.add).setOnClickListener {
//            i++
//            val btn = Button(this)
//            btn.text = "btn $i"
//            container2.addView(btn)
//        }
//        findViewById<Button>(R.id.remove).setOnClickListener {
//            if (i > 0) {
//                container2.removeViewAt(0)
//            }
//            i--
//        }

//        setContentView(R.layout.chapter_5)
//        setContentView(R.layout.chapter_6)
//        setContentView(R.layout.chapter_7)
//        setContentView(R.layout.chapter_8)
//        setContentView(R.layout.chapter_9)
        setContentView(R.layout.chapter_10)
        val bg = findViewById<TextView>(R.id.tv10).background
        Log.i("wmkwmk", "bg-class: ${bg.javaClass}")

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