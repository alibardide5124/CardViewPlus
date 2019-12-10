package com.alibardide5124.cardviewplus;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class CardViewPlus extends CardView {


    private Context context;
    private float normalElevation;
    private float pressedElevation;
    private boolean isAnimationEnabled;
    private int clickMode;

    private int CLICK_MODE_COLLAPSE = 0;
    private int CLICK_MODE_RELEASE = 1;

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
        setNormalElevation(5);
        setPressedElevation(10);
        setAnimationEnabled(true);
        setClickMode(CLICK_MODE_COLLAPSE);

        initElevation();
        setTag("Released");
    }
    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardViewPlus, 0, 0);

        this.context = context;
        setNormalElevation(typedArray.getDimension(R.styleable.CardViewPlus_normalElevation, 5));
        setPressedElevation(typedArray.getDimension(R.styleable.CardViewPlus_pressedElevation, getNormalElevation() * 2));
        setAnimationEnabled(typedArray.getBoolean(R.styleable.CardViewPlus_animationEnabled, true));
        setClickMode(typedArray.getInt(R.styleable.CardViewPlus_clickMode, CLICK_MODE_COLLAPSE));

        typedArray.recycle();

        initElevation();
        setTag("Released");
    }
    private void initElevation() {
        if (getPressedElevation() > getNormalElevation())
            setMaxCardElevation(getPressedElevation());
        else
            setMaxCardElevation(getNormalElevation());

        setCardElevation(getNormalElevation());
    }

    public boolean isAnimationEnabled() {
        return isAnimationEnabled;
    }
    public void setAnimationEnabled(boolean animationEnabled) {
        isAnimationEnabled = animationEnabled;
    }

    public float getNormalElevation() {
        return normalElevation;
    }
    public void setNormalElevation(float normalElevation) {
        this.normalElevation = normalElevation;
    }

    public float getPressedElevation() {
        return pressedElevation;
    }
    public void setPressedElevation(float pressedElevation) {
        this.pressedElevation = pressedElevation;
    }

    public int getClickMode() {
        return clickMode;
    }
    public void setClickMode(int clickMode) {
        this.clickMode = clickMode;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAnimationEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (getTag().equals("Released")) {
                        onTouchAnimation();
                        setTag("Pressed");
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (getTag().equals("Pressed")) {
                        onReleaseAnimation();
                        setTag("Released");
                        setClickable(false);
                        if (clickMode == CLICK_MODE_COLLAPSE) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    performClick();
                                }
                            }, 150);
                        } else if (clickMode == CLICK_MODE_RELEASE) {
                            performClick();
                        } else {
                            performClick();
                        }
                        return false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (getTag().equals("Pressed")) {
                        float eventY = event.getY();
                        float eventX = event.getX();
                        if (eventY > getHeight() || eventX > getWidth() || eventY < 0 || eventX < 0) {
                            onReleaseAnimation();
                            setTag("Released");
                        }
                        return false;
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    if (getTag().equals("Pressed")) {
                        onReleaseAnimation();
                        setTag("Released");
                    }
            }
        }
        return true;
    }

    private void onTouchAnimation() {
        final float normal = getNormalElevation();
        final float pressed = getPressedElevation();

        Animation animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setCardElevation((int) (interpolatedTime * (pressed - normal)) + normal);
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(150);
        startAnimation(animation);
    }
    private void onReleaseAnimation() {
        final float normal = getNormalElevation();
        final float pressed = getPressedElevation();

        Animation animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setCardElevation((int) (normal - (int) (interpolatedTime * (pressed - normal)) + pressed - normal));
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(150);
        startAnimation(animation);
    }
}
