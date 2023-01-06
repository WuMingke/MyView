package com.erkuai.myview

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * 布局过程的完全解析
 *
 * 整体流程：
 *      1、测量流程：从根View递归调用每一级子View的measure()方法，对它们进行测量
 *      2、布局流程：从根View递归调用每一级子View的layout()方法，把测量过程得出的子View的位置和尺寸传给子View，子View保存
 *
 * 为什么要分两个流程？
 *      测量流程较为复杂，有的子View可能需要测量多次，每次测量都导致每次的layout的话，中间过程其实是没有用的
 *
 *          比如有个LinearLayout的宽是wrap_content，第一个子View是的宽是match_parent，第二个子View的宽是200dp
 *          那么在第一次测量的过程中，第一个子View的宽会被测量成0dp，第二个子View的宽测量成200dp，那么LinearLayout就知道它的大小应该是200dp
 *          在第二次的测量中，第一个子View的宽就会被测量成200dp
 *
 *          但是，这种测量方式不是固定的，比如有个LinearLayout的宽是wrap_content，有两个子View的宽都是match_parent，
 *          那么Linearlayout会让它们自由测量，等自由测量有结果了，可以获取到一个最宽的宽度，那么就按照这个宽度，再给子View量一轮，得到最后的结果
 *
 * 个体流程：
 *      1、运行前，开发者在xml文件里写入对View的布局要求 layout_xxx
 *      2、父View在自己的onMeasure()中，根据开发者在xml中写的对子View的要求，和自己的可用空间，得出对子View的具体尺寸要求
 *      3、子View在自己的onMeasure()中，根据父View的要求以及自己的特性算出自己的期望尺寸
 *          如果是ViewGroup，还会在这里调用每个子View的measure()进行测量
 *      4、父View在子View计算出期望尺寸后，得出子View的实际尺寸和位置
 *      5、子View在自己的layout()方法中，将父View传进来的自己的实际尺寸和位置保存
 *          如果是ViewGroup，还会在onLayout()里调用每个子View的layout()把它们的尺寸和位置传给它们
 *
 */
class TestView14(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // 测试下面这行
        // 如果宽高设置 match_parent、match_parent
        // 在ConstraintLayout里，就是全屏的 （子View可以不听父View的设置，但是父View有能力修正）
        // 但是在Linearlayout里，它是100*100
        setMeasuredDimension(100.dp.toInt(), 100.dp.toInt()) // 第一次可以强行修改，不遵守父View的设置
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        // 第二次可以强行修改，不遵守父View的设置，这里是可以绝对更改的
        // 但是由于跳过了父View知道的测量结果，会导致其它View摆放不正常
        super.layout(l + 100.dp.toInt(), t, r, b)
    }


}