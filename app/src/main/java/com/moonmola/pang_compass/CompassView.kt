package com.moonmola.pang_compass

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.RotateDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import com.moonmola.pang_compass.databinding.ViewCompassBinding
import android.R
import android.os.Handler

import android.view.animation.LinearInterpolator

import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator

import android.view.animation.RotateAnimation
import java.util.*
import kotlin.math.abs


class CompassView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private var compassPaint = Paint()
    private var radius: Float = 5f
    private var midX: Float = 0f
    private var midY: Float = 0f
    private var currentDegree: Float = 0f
    private var oldDegree: Float = 0f
    private var binding: ViewCompassBinding
    init {
        compassPaint = Paint().apply {
            style = Paint.Style.FILL
            colorFilter
        }
        val layoutInflater = LayoutInflater.from(context)
        binding = ViewCompassBinding.inflate(layoutInflater,this,true)

    }

    fun init(midX: Float, midY: Float, radius: Float) {
        this.midX = midX
        this.midY = midY
        this.radius = radius

    }

    fun onSensorEvent(newDegree: Float) {
        var from = currentDegree.unaryMinus()
        var to = newDegree.unaryMinus()
        val rotationAngle = (to - from)

        if(abs(rotationAngle)>2) {
            Log.e("???FROM,TO", "$from,    $to,   $rotationAngle")
//            val ra = RotateAnimation(
//                from,
//                to,
//                Animation.RELATIVE_TO_SELF,
//                0.5f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f
//            )
//            ra.interpolator = LinearInterpolator()
//            ra.duration = (abs(rotationAngle)*2).toLong()
//            ra.fillAfter = true
//            binding.compassImage.startAnimation(ra)
//            binding.textLayout.startAnimation(ra)
            currentDegree = newDegree

            val rotation = newDegree.unaryMinus()

            binding.compassViewLayout.rotation = rotation
//            binding.textLayout.rotation = rotation
            binding.east.rotation = -rotation
            binding.west.rotation = -rotation
            binding.north.rotation = -rotation
            binding.south.rotation = -rotation
            binding.eastAngle.rotation = -rotation
            binding.westAngle.rotation = -rotation
            binding.northAngle.rotation = -rotation
            binding.southAngle.rotation = -rotation
            binding.middle.rotation = -rotation
        }
//        binding.east.rotation = -to
//        binding.west.rotation = -to
//        binding.north.rotation = -to
//        binding.south.rotation = -to
    }

    @SuppressLint("UseCompatLoadingForDrawables", "DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

    }

}