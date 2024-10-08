package com.example.winiychat.extension_utils

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import kotlin.math.min


class ExpandableCardBackgroundDrawable(
    private val startColor: Int,
    private val endColor: Int,
    private val initialCornerRadius: Float,
    private var initialDrawColor: Int // 新增：初始绘制颜色
) : Drawable() {


    private var progress: Float = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    init {
        paint.style = Paint.Style.FILL
    }

    // 设置初始颜色的方法
    fun setInitialDrawColor(color: Int) {
        initialDrawColor = color
    }

    override fun draw(canvas: Canvas) {
        val width = bounds.width()
        val height = bounds.height()

        // 首先绘制初始颜色为曲边
        paint.color = initialDrawColor
        path.reset()
        val radius = Math.min(width, height) / 2 * initialCornerRadius
        path.addRoundRect(
            RectF(Rect(0, 0, width, height)),
            radius,
            radius,
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)

        val currentColor = interpolateColor(startColor, endColor, progress)
        paint.color = currentColor


        path.reset()
        val expandedRadius = min(width, height) / 2 * initialCornerRadius * progress
        path.addRoundRect(
            RectF(Rect(0, 0, (width * progress).toInt(), (height * progress).toInt())),
            expandedRadius,
            expandedRadius,
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidateSelf()
    }

    private fun interpolateColor(startColor: Int, endColor: Int, fraction: Float): Int {
        val startA = (startColor shr 24) and 0xFF
        val startR = (startColor shr 16) and 0xFF
        val startG = (startColor shr 8) and 0xFF
        val startB = startColor and 0xFF

        val endA = (endColor shr 24) and 0xFF
        val endR = (endColor shr 16) and 0xFF
        val endG = (endColor shr 8) and 0xFF
        val endB = endColor and 0xFF

        val a = (startA + (fraction * (endA - startA)).toInt()).coerceAtLeast(0).coerceAtMost(255)
        val r = (startR + (fraction * (endR - startR)).toInt()).coerceAtLeast(0).coerceAtMost(255)
        val g = (startG + (fraction * (endG - startG)).toInt()).coerceAtLeast(0).coerceAtMost(255)
        val b = (startB + (fraction * (endB - startB)).toInt()).coerceAtLeast(0).coerceAtMost(255)

        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }
}