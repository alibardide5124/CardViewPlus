package com.alibardide5124.cardviewplus

import android.animation.ArgbEvaluator
import android.animation.FloatEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.cardview.widget.CardView

class CardViewPlus : CardView {

    var normalCardElevation = 5f
    var pressedCardElevation = 10f
    var cardPressedColor = cardBackgroundColor.defaultColor
    var isAnimationEnabled = true
    var clickDelay = 150
    private var isCardPressed = false
    private val animationDuration = 150
    private val defaultColor = cardBackgroundColor.defaultColor

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }
    
    private fun init(context: Context, attrs: AttributeSet? = null) {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardViewPlus, 0, 0)
            normalCardElevation = typedArray.getDimension(R.styleable.CardViewPlus_normalCardElevation, normalCardElevation)
            pressedCardElevation = typedArray.getDimension(R.styleable.CardViewPlus_pressedCardElevation, normalCardElevation * 2)
            cardPressedColor = typedArray.getColor(R.styleable.CardViewPlus_cardPressedColor, cardPressedColor)
            isAnimationEnabled = typedArray.getBoolean(R.styleable.CardViewPlus_animationEnabled, isAnimationEnabled)
            clickDelay = typedArray.getInt(R.styleable.CardViewPlus_clickDelay, clickDelay)
            typedArray.recycle()
        } 
        initElevation()
    }

    private fun initElevation() {
        maxCardElevation = if (pressedCardElevation > normalCardElevation) pressedCardElevation else normalCardElevation
        cardElevation = normalCardElevation
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
                    val checkX = event.x < 0 || event.x > width
                    val checkY = event.y < 0 || event.y > height
                    if (checkX || checkY) {
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
        } else
            super.onTouchEvent(event)
        return true
    }

    private fun onTouchAnimation() {
        touchAnimation().start()
        colorAnimation().start()
    }

    private fun onReleaseAnimation() {
        touchAnimation().reverse()
        colorAnimation().reverse()
    }

    private fun touchAnimation() : ObjectAnimator = ObjectAnimator.ofObject(
            this,
            "cardElevation",
            FloatEvaluator(),
            normalCardElevation,
            pressedCardElevation
    ).setDuration(animationDuration.toLong())

    private fun colorAnimation() : ObjectAnimator = ObjectAnimator.ofObject(
            this,
            "cardBackgroundColor",
            ArgbEvaluator(),
            defaultColor,
            cardPressedColor
    ).setDuration(animationDuration.toLong())
}