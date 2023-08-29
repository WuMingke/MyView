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

    层级：

    透明图层（源图像）         drawBitmap：拿什么应用给Xfermode
    透明画布（目标图像）       调用saveLayer产生的画布
    原始画布                  原始画布

    每次调用canvas.drawXXX函数时，都会生成一个透明的图层来专门绘制这个图形，每次生成的这个都会叠加到
    最近的画布上，

    图层和画布：
    图层（Layer）：每次调用canvas.drawXXX函数，都会生成一个透明图层专门来绘制这个图形
    画布（Bitmap）：每块画布都是一个Bitmap，所有的图像都是画在这个Bitmap上的。在图层上绘制完成以后，
                就覆盖到画布上。如果连续调用5次drawXXX函数，就会生成5个透明图层，画完之后依次覆盖在画布上显示，
                这里就有了上下的关系。画布有两种，一种是View的原始画布，通过重写的onDraw函数传入，另一种是通过
                savaLayer、new Canvas()等函数新建的，savaLayer之后，所有的draw函数所画的图像都是画在这块画布上的，
                直到调用restore、restoreToCount函数之后，才会返回到原始画布上绘制。
    Canvas：Canvas是画布的表现形式，无论是原始画布还是new的画布，所有的画布最后都通过Canvas会知道Bitmap上，
                可以把Canvas理解成绘图的工具，利用它封装的绘图函数来绘图。所以如果利用canvas.clipXXX()将
                画布进行裁剪，其实就是把对应的Bitmap进行裁剪。





Bitmap和Drawable
    TestView13

手写MaterialEditText

布局过程的完全解析
    TestView14

自定义布局-尺寸的自定义
    TestView15

自定义布局-

