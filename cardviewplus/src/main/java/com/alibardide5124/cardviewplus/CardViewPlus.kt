package com.alibardide5124.cardviewplus

import android.animation.FloatEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.cardview.widget.CardView

class CardViewPlus : CardView {

    var normalElevation = 5f
    var pressedElevation = 10f
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
                    val eventY = event.y
                    val eventX = event.x
                    if (eventY > height || eventX > width || eventY < 0 || eventX < 0) {
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

    private fun onTouchAnimation() {
        ObjectAnimator.ofObject(
                this,
                "cardElevation",
                FloatEvaluator(),
                normalElevation,
                pressedElevation
        ).setDuration(animationDuration.toLong())
                .start()
    }

    private fun onReleaseAnimation() {
        ObjectAnimator.ofObject(
                this,
                "cardElevation",
                FloatEvaluator(),
                pressedElevation,
                normalElevation
        ).setDuration(animationDuration.toLong())
                .start()
    }
}