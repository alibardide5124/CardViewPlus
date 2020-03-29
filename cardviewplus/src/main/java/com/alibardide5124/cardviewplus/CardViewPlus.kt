package com.alibardide5124.cardviewplus

import android.animation.ArgbEvaluator
import android.animation.FloatEvaluator
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.cardview.widget.CardView

class CardViewPlus : CardView {

    var normalElevation = 5f
    var pressedElevation = 10f
    var pressedColor = Color.parseColor("#ffe0e0e0")
    var isAnimationEnabled = true
    var clickDelay = 150
    private var isCardPressed = false
    private val animationDuration = 150

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init() {
        initElevation()
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardViewPlus, 0, 0)
        normalElevation = typedArray.getDimension(R.styleable.CardViewPlus_normalElevation, normalElevation)
        pressedElevation = typedArray.getDimension(R.styleable.CardViewPlus_pressedElevation, normalElevation * 2)
        pressedColor = typedArray.getColor(R.styleable.CardViewPlus_pressedColor, pressedColor)
        isAnimationEnabled = typedArray.getBoolean(R.styleable.CardViewPlus_animationEnabled, isAnimationEnabled)
        clickDelay = typedArray.getInt(R.styleable.CardViewPlus_clickDelay, clickDelay)
        typedArray.recycle()
        initElevation()
    }

    private fun initElevation() {
        maxCardElevation = if (pressedElevation > normalElevation) pressedElevation else normalElevation
        cardElevation = normalElevation
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        onTouchEvent(ev)
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isAnimationEnabled) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> if (!isCardPressed) {
                    onTouchAnimation()
                    isCardPressed = true
                }
                MotionEvent.ACTION_UP -> if (isCardPressed) {
                    onReleaseAnimation()
                    isCardPressed = false
                    isClickable = false
                    Handler().postDelayed({ performClick() }, clickDelay.toLong())
                    return false
                }
                MotionEvent.ACTION_MOVE -> if (isCardPressed) {
                    if (event.y > height || event.x > width || event.y < 0 || event.x < 0) {
                        onReleaseAnimation()
                        isCardPressed = false
                    }
                    return false
                }
                MotionEvent.ACTION_CANCEL -> if (isCardPressed) {
                    onReleaseAnimation()
                    isCardPressed = false
                }
            }
        }
        return true
    }

    private fun touchAnimation() : ObjectAnimator = ObjectAnimator.ofObject(
            this,
            "cardElevation",
            FloatEvaluator(),
            normalElevation,
            pressedElevation
        ).setDuration(animationDuration.toLong())

    private val defaultColor = cardBackgroundColor.defaultColor
    private fun colorAnimation() : ObjectAnimator = ObjectAnimator.ofObject(
            this,
            "cardBackgroundColor",
            ArgbEvaluator(),
            defaultColor,
            pressedColor
    ).setDuration(animationDuration.toLong())

    private fun onTouchAnimation() {
        touchAnimation().start()
        colorAnimation().start()
    }

    private fun onReleaseAnimation() {
        touchAnimation().reverse()
        colorAnimation().reverse()
    }


}