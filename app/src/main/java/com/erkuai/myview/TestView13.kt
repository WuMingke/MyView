package com.erkuai.myview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable

/**
 * Bitmap 和 Drawable
 */
class TestView13 {

    /**
     * Drawable 转 Bitmap
     */
    fun covertDrawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        bitmap = if (width <= 0 || height <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // 这里感觉可以不用
        } else {
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    // 另一种方法
    fun covertDrawableToBitmap2(drawable: Drawable): Bitmap? {
        return drawable.toBitmap()
    }

    /**
     * Bitmap 转 Drawable
     */
    fun convertBitmapToDrawable(resources: Resources, bitmap: Bitmap): Drawable {
        return BitmapDrawable(resources, bitmap)
    }

    // 另一种方法
    fun convertBitmapToDrawable2(resources: Resources, bitmap: Bitmap): Drawable {
        return bitmap.toDrawable(resources)
    }

    /**
     * Bitmap是什么
     *  位图，像素数据的映射，是存储图片信息的
     * Drawable是什么
     *  它不是图片信息的存储工具，而是一个绘制工具
     *  它是可以调用canvas的上层工具，它的角色跟View的是相当的
     *  它跟View又有区别，View可以测量、布局、绘制等，Drawable只负责绘制
     *  eg:DrawableView
     *
     *  Bitmap 和 Drawable 互转，其实就是用一个对象，创建了另一个对象
     *
     *  Drawable的自定义：
     *  eg:MeshDrawable
     *  自定义的作用：
     *      方便和重用
     *
     *  Bitmap 是final的，不能别继承
     *
     */

}