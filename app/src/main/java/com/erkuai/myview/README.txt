所有绘制时的单位都是像素px，而不是dp

Path.Direction.CCW
Path.Direction.CW
Path.FillType.EVEN_ODD 等
    多个图形相交时，根据绘制方向和填充规则，判断相交部分应该是填充还是留空

PathMeasure 测量Path

图形的位置和尺寸测量
TestView2、TestView3

Xfermode完全使用解析（离屏缓冲）
TestView4、TestView5

文字的测量
TestView6 居中1
纵向居中的是baseline，显示都基于baseline
TestView7 对齐
TestView8 多行绘制

范围裁切和几何变换
    TestView9：Canvas的范围裁切:clipRect、clipPath（clipOutPath）、、
    TestView10：1、Canvas的几何变换:translate、rotate、scale、skew
                    canvas变动，它的坐标系变动了，那么变换的时候，如果是站在canvas的角度思考，那么需要考虑坐标的方向
                    如果是站在图像的角度思考，那么代码倒着写，比较方便思考
                2、Matrix的几何变 换：preTranslate/postTranslate、、、
                3、Camera:
    TestView11

属性动画:MainActivity
    1、viewPropertyAnimator:
    2、ObjectAnimator:
    3、AnimatorSet
    4、PropertyValuesHolder
    5、Keyframe


