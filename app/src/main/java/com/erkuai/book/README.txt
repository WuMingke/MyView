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

在Java中有一个Math类，输入参数是 弧度值
double sin(double a)
double cos(double a)
double tan(double a)
且 角度到弧度的api
double toRadians(double angdeg)

所以，22度对应的正弦值是 Math.sin(Math.toRadians(22))

反正切，并弧度转角度
val degrees = Math.toDegrees(atan2(tan[1].toDouble(), tan[0].toDouble()))


4
PropertyValuesHolder => 保存了动画过程中所需要操作的属性和对应的值
Keyframe => 关键帧，定义每一帧在哪一个进度展示
ViewPropertyAnimator => 便捷的属性动画  view.animate().alpha(xx)
    相比较ObjectAnimator，ViewPropertyAnimator并没有像ObjectAnimator一样使用反射或者JNI技术，而ViewPropertyAnimator
    会根据预设的每一个动画帧计算出对应的所有属性，并设置给控件，然后调用一次invalidate()函数进行重绘，从而解决了在使用ObjectAnimator
    时每个属性单独计算，单独重绘的问题，所以ViewPropertyAnimator相对于ObjectAnimator和组合动画，性能有所提升

为ViewGroup内的子View添加动画
1 layoutAnimation   针对listView的item入场动画，新添加不会有效果
2 gridLayoutAnimation   针对gridView的item入场动画，新添加不会有效果
3 android:animateLayoutChanges属性    针对所有ViewGroup的子View添加或删除，动画不能自定义
4 LayoutTransition  针对所有ViewGroup的子View添加或删除，动画可以自定义

5
路径动画，依赖于 PathMeasure
用 ValueAnimator 动画产生 0-1 的进度，使用 PathMeasure 得到path的长度，进度*长度 = 动画 --》PathMeasureView
getLength()
nextContour() // 跳转到下一条曲线
public boolean getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)
public boolean getPosTan(float distance, float pos[], float tan[])
public boolean getMatrix(float distance, Matrix matrix, int flags) // 根据 flags 获取对应的 matrix 值

6
硬件加速
    控制硬件加速4个层级
    Application         android:hardwareAccelerated="false"
    Activity            android:hardwareAccelerated="false"
    Window                   window?.setFlags(
                                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                                )
    View                1、setLayerType(LAYER_TYPE_SOFTWARE, null)
                        2、android:layerType="software"

7
贝塞尔曲线
path.moveTo(sx,sy) // 到起始点
path.quadTo(cx,cy,ex,ey) // 二阶贝塞尔曲线，控制点、终点

setShadowLayer(float radius, float dx, float dy, long shadowColor) // 设置阴影效果，
    这个只能实现产生一张新图片，然后再对周围阴影
使用的是高斯模糊算法：对于正在处理的每一个像素，取周围若干个像素的RGB值并且平均，这个平均值就是模糊处理过的像素。
如果对图片中的所有像素都这么处理，那么处理完成的图片就会变得模糊。其中，所取周围像素的半径就是模糊半径。所以，
模糊半径越大，所得平均像素与原始像素差别就越大，也就越模糊。

TextView:
android:shadowDx="5"
android:shadowDy="5"
android:shadowRadius="5"

setMaskFilter(MaskFilter maskfilter) // 设置发光效果
        BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)

给图片添加纯色阴影
    1 bitmap.extractAlpha() // 对图片产生一张只包含相同透明度的图片
    2 setMaskFilter 使其内外发光
    2 偏移绘制这个图片

8
混合模式Xfermode
    在setXfermode之前的图像是目标图像，即给谁应用Xfermode
    在setXfermode之后的是源图像，即拿什么应用Xfermode

9
Canvas与图层
    在绘制View的时候，重写onDraw
    在绘制ViewGroup的时候，重写dispatchDraw，当它有背景的时候，才会走onDraw

获取Canvas对象的方法
    1 重写onDraw、dispatchDraw
    2 通过bitmap创建      private var canvas = Canvas(bitmap)
    3 SurfaceHolder.lockCanvas() // SurfaceView的双缓冲机制

10
shape标签对应的是 GradientDrawable

Drawable和Bitmap
自定义的Drawable的使用场景很明确
    1 使用在可以设置Drawable的函数中，如setImageDrawable
    2 代替Bitmap用于View中

Bitmap是Drawable，但是Drawable不一定是Bitmap
Bitmap在内存占用和绘制速度上不如Drawable有优势
Bitmap绘图方便，而Drawable调用paint方便，但调用Canvas并不方便
Drawable有一些子类，可以方便地完成一些绘图功能，比如ShapeDrawable、GradientDrawable

Bitmap Drawable 自定义View 使用场景：
1 Bitmap只在一种情况下使用，就是在View中需要自己生成图像时。绘图后的结果保存在这个Bitmap中，
    供自定义View使用。
2 当使用Drawable的子类能完成一些固有功能时，优先选用Drawable
3 当使用setImageDrawable 等可以直接设置Drawable资源的函数时，只能用Drawable
4 除Bitmap Drawable以外的地方，都可以使用自定义View

Bitmap在绘图中主要的两种使用场景：
1 转换为BitmapDrawable
        val bitmap: Bitmap = BitmapFactory.decodeResource(res, R.drawable.avatar)
        val bitmapDrawable: BitmapDrawable = BitmapDrawable(res, bitmap)
2 作为画布
        val bitmap: Bitmap = ...
        val canvas: Canvas = Canvas(bitmap)
        canvas.drawXXX

一张位图所占内存 = 长度(px) * 宽度(px) * 一个像素点占用的字节数
ALPHA_8          8-1个字节
ARGB_4444 4+4+4+4=16-2个字节
ARGB_8888 8+8+8+8=32-4个字节
RGB_565   5+6+5=16-2个字节
而从资源文件中加载的话，还要考虑分辨率的对应关系。缩放比例 = 屏幕分辨率/文件夹所对应的分辨率
从sd卡中加载的话，不进行缩放。



fun getAvatar(resources: Resources, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
}

inJustDecodeBounds  表示只解析图片信息(inJustDecodeBounds = true)，这样可以不将图片加载到内存中，得到图片宽高
inSampleSize    采样率。指每隔多少个样本采样一次作为结果。
                比如设置为4，那么就是每隔4个像素取一个，其余的丢弃，那么宽高就变为原来的1/4
                采样率的设置要使缩放后的图片尺寸尽量大于等于相应的ImageView的大小。
inScaled    在需要缩放的时候，是否缩放，默认true
inDensity   设置文件所在资源文件夹的屏幕分辨率
inTargetDensity     表示真实显示的屏幕分辨率
所以上面的 缩放比例 = 屏幕分辨率/文件夹所对应的分辨率
就等于 scale = inTargetDensity / inDensity
所以这两个参数的作用就是：可以通过手动设置文件所在资源文件夹的分辨率和真实显示的屏幕分辨率来指定图片的缩放比例


SurfaceView
    在两个方面改进了View的绘图操作
    1 使用双缓冲技术。就是多加一块缓冲画布，当需要执行绘图操作时，先在缓冲画布上绘制，绘制好后直接将缓冲画布上的内容
        更新到主画布上。这样在屏幕更新时，只需把缓冲画布上的内容照样画过来就可以了，就不会存在逻辑处理时间的问题，也就解决
        了超时绘制的问题。
    2 自带画布，可以在子线程中更新画布内容。

当界面被动更新时，比如手势交互，使用View
当界面是主动更新时，或者频繁刷新，或者刷新的处理量比较大时，比如视频、摄像头，使用SurfaceView

SurfaceView是一个mvc的结构
Surface：model
SurfaceView：view
SurfaceHolder：controller

val lockCanvas = holder.lockCanvas()
// 绘制
holder.unlockCanvasAndPost(lockCanvas)
双缓冲技术需要两个图形缓冲区，一个是前端缓冲区，一个是后端缓冲区。前端缓冲区对应当前屏幕正在显示的内容，
后端缓冲区是接下来要渲染的图形缓冲区。通过holder.lockCanvas()获得的是后端缓冲区。当绘图完成以后，
调用holder.unlockCanvasAndPost(lockCanvas)将后端缓冲区与前端缓冲区交换，后端变成前端，前端变成后端，
等待下一次lockCanvas()，如此往复。

MeasureSpec.UNSPECIFIED // 子元素可以得到任意想要的大小
MeasureSpec.EXACTLY // match_parent 、 具体值
MeasureSpec.AT_MOST // 至多 wrap_content

val widthMode = MeasureSpec.getMode(widthMeasureSpec)
val measureWidth = MeasureSpec.getSize(widthMeasureSpec) // 测量的宽
// 如果是模式是用户设置的，那么不应该取修改，如果是wrap_content，那么就需要控件去计算大小
val width = if (widthMode == MeasureSpec.EXACTLY) {
    measureWidth
} else {
    10 // 计算的宽
}
setMeasuredDimension(width, width)

getMeasuredWidth(): 在measure()过程结束后可以取到值，它的值是通过setMeasuredDimension()确定的
getWidth():在layout()结束后可以取到值，它的值是通过layout确定的









