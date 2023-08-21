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

插值器
    private fun testValueAnimator(view: View) {
        val animator = ValueAnimator.ofInt(0, 400)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            view.layout(curValue, 0, curValue + view.width, 0)
        }
        animator.start()
    }



