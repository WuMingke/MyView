package com.erkuai.book.chapter10

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.concurrent.thread

class CustomSurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 2f
        textSize = 25f
        color = Color.GRAY
        style = Paint.Style.STROKE
    }

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                drawText(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }

        })

    }

    private fun drawText(holder: SurfaceHolder) {
        thread {
            for (i in 0..9) {
                val lockCanvas = holder.lockCanvas()
                // 绘制
                lockCanvas?.drawText("$i", i * 30f, 50f, mPaint)
                holder.unlockCanvasAndPost(lockCanvas)

                Thread.sleep(2000)
            }
        }
    }
}