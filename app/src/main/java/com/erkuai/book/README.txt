1
Region类

Rect类
    RectF.intersects() 判断两个矩形是否相交
    mRect.contains     矩形是否包含点、另外的矩形
    mRect.union()       合并矩形

对于色彩的存储，Bitmap类使用一个32位的数值来保存，红、绿、蓝、透明，各占8位。
颜色矩阵
    Red   0    0    0
    0   Green  0    0
    0     0   Blue  0
    0     0    0   Alpha

ColorMatrix 颜色变换


2 动画
View Animation  视图动画（xml标签）
    TweenAnimation  补间动画
    FrameAnimation  帧动画
Property Animation  属性动画
    ValueAnimator
    ObjectAnimator

在动画scale标签中，pivotX、pivotY取不同值的含义
三种
1 数值        50          当前控件的左上角+50
2 百分比      50%         当前控件的左上角+自己的50%宽或者高
3 百分比p     50%p         当前控件的左上角+父控件的50%宽或者高


3
    private fun testValueAnimator(view: View) {
        val animator = ValueAnimator.ofInt(0, 400)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            view.layout(curValue, curValue, curValue + view.width, curValue + view.height)
        }
        animator.duration = 1000
        animator.start()
    }

    ValueAnimator 计算值的
    curValue 其实就是  当前的值 = 0 + （400 - 0）* 当前动画的进度  的结果
    这个进度 与 插值器相关


自定义插值器
class MyInterpolator : TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        return 1 - input
    }
}
input: 当前动画的进度

ofInt(0, 400)   ----->   插值器   ----->    Evaluator   ----->   监听器返回
定义动画数值区间        返回当前数值进度     根据当前数值进度计算当前值    在AnimatorUpdateListener中返回

自定义估值器
class MyEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return 200 + startValue + (fraction * (endValue - startValue)).toInt()
    }
}

ValueAnimator缺点：只能对动画中的数值进行计算，如果要针对控件，只能添加监听，比较繁琐
ObjectAnimator，继承自 ValueAnimator
    通过指定属性所对应的set函数来改变值
    然后通过反射找到对应控件的set函数，并将当前数值作为函数参数传入，改变了属性值，最后调用invalidate()强制重绘，产生了动画

自定义ObjectAnimator属性  FallingBallImageView
            val animator = ObjectAnimator.ofObject(
                view, "fallingPos", FallingBallEvaluator(), Point(0, 0), Point(500, 500)
            )
            当且仅当只给动画设置一个值时，程序才会调用属性对应的get函数来得到动画的初始值。
            这里如果没有 Point(0, 0) 会报错

路径动画



