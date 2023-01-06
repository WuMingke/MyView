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
    6、interpolator
    7、TypeEvaluator
    8、Listeners
    9、ValueAnimator // 基本不用

硬件加速
    软件绘制，CPU绘制，整个界面的绘制都是基于一个bitmap，每一个View都往整个bitmap上绘制
    硬件绘制，GPU绘制，cpu在绘制阶段（canvas.drawXXX时），cpu将方法转换成GPU的操作，
        在屏幕显示的时候，GPU再将这些操作应用到屏幕上，在这个应用的过程中，把这些操作变成
        了像素。用GPU绘制，可以将绘制流程加快，也就是硬件加速。也就是说硬件绘制和硬件加速，
        其实是一个东西。
        怎么就"加速"了？
        1、CPU+GPU一起工作，效率高
        2、GPU这种硬件，专门用来做绘制的
        3、有GPU的帮助，使得绘制流程得到优化，重绘的时候效率更高（只需重绘改变的操作）
        限制：GPU绘制有兼容性问题，开启了之后，有些api会失效，官方文档上有列举
    离屏缓冲：
        hardware_layer 代替 saveLayer （saveLayer是为这一次绘制设置一个离屏缓冲）
        它的原理是用硬件为这个View设置一个离屏缓冲
        api:  setLayerType(LAYER_TYPE_HARDWARE, null)
        LAYER_TYPE_HARDWARE:开启这个View的离屏缓冲，并且用硬件绘制实现缓冲
        LAYER_TYPE_SOFTWARE:开启这个View的离屏缓冲，并且用软件绘制实现缓冲
        LAYER_TYPE_NONE：关闭这个View的离屏缓冲
    全局的硬件加速，在AndroidManifest里配置
    单个的View，没有硬件加速，只是设置离屏缓冲
    所以说如果打开了全局的，想要关闭某个单独的View的硬件减速，使用LAYER_TYPE_SOFTWARE，
        那么它相当于就是使用软件绘制，实际上就是间接关闭了硬件加速。
    View级别的离屏缓冲的作用：其中有一个作用是在属性动画的场景，开启LAYER_TYPE_HARDWARE的状态下，
        对属性动画自带的属性(translation、rotation等)，再调用 .withLayer() ,
        在动画过程中开启一个离屏缓冲，让动画在渲染的时候更快，动画结束后自动关闭

Bitmap和Drawable
    TestView13

手写MaterialEditText

布局过程的完全解析
    TestView14

自定义布局-尺寸的自定义
    TestView15

自定义布局-

