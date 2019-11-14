package com.alibardide5124.cardviewplus;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class CardViewPlus extends CardView {


    private Context context;
    private float minElevation;
    private float maxElevation;
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
        setMinElevation(5);
        setMaxElevation(10);
        setAnimationEnabled(true);
        setClickMode(CLICK_MODE_COLLAPSE);

        CardTouch();
    }
    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardViewPlus, 0, 0);

        this.context = context;
        setMinElevation(typedArray.getDimension(R.styleable.CardViewPlus_minElevation, 5));
        setMaxElevation(typedArray.getDimension(R.styleable.CardViewPlus_maxElevation, 10));
        setAnimationEnabled(typedArray.getBoolean(R.styleable.CardViewPlus_animationEnabled, true));
        setClickMode(typedArray.getInt(R.styleable.CardViewPlus_clickMode, CLICK_MODE_COLLAPSE));

        typedArray.recycle();

        if (isAnimationEnabled)
            CardTouch();
    }

    public boolean isAnimationEnabled() {
        return isAnimationEnabled;
    }
    public void setAnimationEnabled(boolean animationEnabled) {
        isAnimationEnabled = animationEnabled;
    }

    public float getMinElevation() {
        return minElevation;
    }
    public void setMinElevation(float minElevation) {
        this.minElevation = minElevation;
    }

    public float getMaxElevation() {
        return maxElevation;
    }
    public void setMaxElevation(float maxElevation) {
        this.maxElevation = maxElevation;
    }

    public int getClickMode() {
        return clickMode;
    }
    public void setClickMode(int clickMode) {
        this.clickMode = clickMode;
    }

    public void CardTouch() {
        setMaxCardElevation(getMaxElevation());
        setCardElevation(getMinElevation());
        setTag("Released");
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (getTag().equals("Released")) {
                            expand();
                            setTag("Pressed");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (getTag().equals("Pressed")) {
                            collapse();
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
                                collapse();
                                setTag("Released");
                            }
                            return false;
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void expand() {
        final float min = getMinElevation();
        final float max = getMaxElevation();

        Animation animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setCardElevation((int) (interpolatedTime * (max - min)) + min);
            } @Override
        public boolean willChangeBounds() {
            return true;
        }
        };
        animation.setDuration(150);
        startAnimation(animation);
    }
    private void collapse() {
        final float min = getMinElevation();
        final float max = getMaxElevation();

        Animation animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setCardElevation((int) (min - (int) (interpolatedTime * (max - min)) + max - min));
            } @Override
        public boolean willChangeBounds() {
            return true;
        }
        };
        animation.setDuration(150);
        startAnimation(animation);
    }
}
