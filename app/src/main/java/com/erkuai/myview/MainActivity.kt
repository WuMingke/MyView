package com.erkuai.myview

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.graphics.Interpolator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_animate) // 动画测试

        findView()

//        testAnimate1() // ViewPropertyAnimator，不能对自定义属性
//        testAnimate2() // ObjectAnimator，不能对多个属性
//        testAnimate3() // AnimatorSet，一个view的多个属性做动画
//        testAnimate4() // PropertyValuesHolder，同一个动画操作多个属性,把事情做细
//        testAnimate5() // Keyframe 更细

//        testAnimate6() // Interpolator 插值器
        testAnimate7() // TypeEvaluator 估值器

    }

    private fun findView() {
        view = findViewById(R.id.view)
    }


    private fun testAnimate1() { // ImageView
        view.animate()
            .translationX(200.dp)
            .translationY(100.dp)
            .scaleX(2f)
            .rotation(45f)
            .startDelay = 1000
    }

    private fun testAnimate2() { // CircleView
        val objectAnimator = ObjectAnimator.ofFloat(view, "radius", 50.dp, 150.dp)
        objectAnimator.startDelay = 1000
        objectAnimator.start()
    }

    private fun testAnimate3() { // TestView12

        val bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 0f, 60f)
        bottomFlipAnimator.startDelay = 1000
        bottomFlipAnimator.duration = 1000

        val rotationFlipAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 30f, 270f)
        rotationFlipAnimator.startDelay = 200
        rotationFlipAnimator.duration = 1000

        val topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", 0f, -60f)
        topFlipAnimator.startDelay = 200
        topFlipAnimator.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomFlipAnimator, rotationFlipAnimator, topFlipAnimator)
        animatorSet.start()
    }

    private fun testAnimate4() { // TestView12
        val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 0f, 60f)
        val rotationFlip = PropertyValuesHolder.ofFloat("flipRotation", 30f, 270f)
        val topFlip = PropertyValuesHolder.ofFloat("topFlip", 0f, -60f)
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, bottomFlipHolder, rotationFlip, topFlip)
        objectAnimator.startDelay = 1000
        objectAnimator.duration = 2000
        objectAnimator.start()
    }

    private fun testAnimate5() { // ImageView

        val final = 200.dp
        val key1 = Keyframe.ofFloat(0f, 0f)
        val key2 = Keyframe.ofFloat(0.2f, 0.4f * final)
        val key3 = Keyframe.ofFloat(0.8f, 0.6f * final)
        val key4 = Keyframe.ofFloat(1f, 1f * final)
        // 把同一个属性的变化再细分
        val keyHolder = PropertyValuesHolder.ofKeyframe("translationX", key1, key2, key3, key4)
        val animator = ObjectAnimator.ofPropertyValuesHolder(view, keyHolder)
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()

    }

    private fun testAnimate6() { // ImageView
        val animator = ObjectAnimator.ofFloat(view, "translationX", 0f, 200.dp)
        animator.startDelay = 1000
        animator.duration = 1000
//        animator.interpolator = AccelerateDecelerateInterpolator()
//        animator.interpolator = AccelerateInterpolator()
//        animator.interpolator = DecelerateInterpolator()
//        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    private fun testAnimate7() { // ImageView
        // 
        // TODO: 2023/1/4 mingKE  
        
    }

}