package com.alibardide5124.cardviewplus;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class CardViewPlus extends CardView {


    private Context context;
    private float normalElevation = 5;
    private float pressedElevation = 10;
    private boolean isAnimationEnabled = true;
    private int clickDelay = 150;

    private int animationDuration = 150;
    private boolean isPressed = false;


    public CardViewPlus(@NonNull Context context) {
        super(context);
        init(context);
    }
    public CardViewPlus(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    public CardViewPlus(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context) {
        this.context = context;

        initElevation();
    }
    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardViewPlus, 0, 0);

        this.context = context;
        normalElevation = (typedArray.getDimension(R.styleable.CardViewPlus_normalElevation, normalElevation));
        pressedElevation = typedArray.getDimension(R.styleable.CardViewPlus_pressedElevation, normalElevation * 2);
        isAnimationEnabled = typedArray.getBoolean(R.styleable.CardViewPlus_animationEnabled, isAnimationEnabled);
        clickDelay = typedArray.getInt(R.styleable.CardViewPlus_clickMode, clickDelay);

        typedArray.recycle();
        initElevation();
    }
    private void initElevation() {
        if (pressedElevation > normalElevation)
            setMaxCardElevation(pressedElevation);
        else
            setMaxCardElevation(normalElevation);

        setCardElevation(normalElevation);
    }

    public boolean isAnimationEnabled() { return isAnimationEnabled; }
    public void setAnimationEnabled(boolean animationEnabled) { isAnimationEnabled = animationEnabled; }

    public float getNormalElevation() { return normalElevation; }
    public void setNormalElevation(float normalElevation) { this.normalElevation = normalElevation; }

    public float getPressedElevation() { return pressedElevation; }
    public void setPressedElevation(float pressedElevation) { this.pressedElevation = pressedElevation; }

    public int getClickDelay() { return clickDelay; }
    public void setClickDelay(int clickDelay) { this.clickDelay = clickDelay; }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAnimationEnabled) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!isPressed) {
                        onTouchAnimation();
                        isPressed = true;
                    } break;
                case MotionEvent.ACTION_UP:
                    if (isPressed) {
                        onReleaseAnimation();
                        isPressed = false;
                        setClickable(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() { performClick(); }
                        }, clickDelay);
                        return false;
                    } break;
                case MotionEvent.ACTION_MOVE:
                    if (isPressed) {
                        float eventY = event.getY();
                        float eventX = event.getX();
                        if (eventY > getHeight() || eventX > getWidth() || eventY < 0 || eventX < 0) {
                            onReleaseAnimation();
                            isPressed = false;
                        } return false;
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    if (isPressed) {
                        onReleaseAnimation();
                        isPressed = false;
                    }
            }
        } return true;
    }

    private void onTouchAnimation() {
        ObjectAnimator.ofObject(
                this,
                "cardElevation",
                new FloatEvaluator(),
                normalElevation,
                pressedElevation
        ).setDuration(animationDuration)
                .start();
    }
    private void onReleaseAnimation() {
        ObjectAnimator.ofObject(
            this,
                "cardElevation",
                new FloatEvaluator(),
                pressedElevation,
                normalElevation
        ).setDuration(animationDuration)
                .start();
    }
}
