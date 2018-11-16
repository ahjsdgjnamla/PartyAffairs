package com.hrdl.partyaffairs.utils.ZWShadowHelperUtil;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zhangwei on 2018/9/14.
 */
public class ZWCustomShadowBackground extends Drawable {

    @ColorInt
    private int mColor;
    @ColorInt
    private int mShadowColor;
    @Nullable
    private int[] mGradientColorArray;
    @Nullable
    private float[] mGradientPositions;
    @Nullable
    private LinearGradient mLinearGradient;
    private int mRadius;
    private int mShadowRadius;
    private int mOffsetX;
    private int mOffsetY;
    @Nullable
    private RectF mRectF;
    @Nullable
    private Paint mPaint;

    protected ZWCustomShadowBackground(@ColorInt int color, @Nullable int[] colorArray, @Nullable float[] gradientPositions, @ColorInt int shadowColor, @Nullable LinearGradient linearGradient, int radius, int shadowRadius, int offsetX, int offsetY) {
        this.mColor = color;
        this.mGradientColorArray = colorArray;
        this.mGradientPositions = gradientPositions;
        this.mShadowColor = shadowColor;
        this.mLinearGradient = linearGradient;
        this.mRadius = radius;
        this.mShadowRadius = shadowRadius;
        this.mOffsetX = offsetX;
        this.mOffsetY = offsetY;
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.mRectF == null) {
            Rect bounds = this.getBounds();
            this.mRectF = new RectF((float)(bounds.left + this.mShadowRadius - this.mOffsetX), (float)(bounds.top + this.mShadowRadius - this.mOffsetY), (float)(bounds.right - this.mShadowRadius - this.mOffsetX), (float)(bounds.bottom - this.mShadowRadius - this.mOffsetY));
        }

        if (this.mPaint == null) {
            this.initPaint();
        }

        canvas.drawRoundRect(this.mRectF, (float)this.mRadius, (float)this.mRadius, this.mPaint);
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @SuppressLint("WrongConstant")
    public int getOpacity() {
        return -3;
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setShadowLayer((float)this.mShadowRadius, (float)this.mOffsetX, (float)this.mOffsetY, this.mShadowColor);
        if (this.mGradientColorArray != null && this.mGradientColorArray.length > 1) {
            boolean isGradientPositions = this.mGradientPositions != null && this.mGradientPositions.length > 0 && this.mGradientPositions.length == this.mGradientColorArray.length;
            this.mPaint.setShader(this.mLinearGradient == null ? new LinearGradient(0.0F, this.mRectF.top, 0.0F, this.mRectF.bottom, this.mGradientColorArray, isGradientPositions ? this.mGradientPositions : null, Shader.TileMode.CLAMP) : this.mLinearGradient);
        } else {
            this.mPaint.setColor(this.mColor);
        }

    }

}
