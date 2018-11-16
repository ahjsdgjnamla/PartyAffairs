package com.hrdl.partyaffairs.utils.ZWShadowHelperUtil;

import android.graphics.LinearGradient;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/**
 * Created by zhangwei on 2018/9/14.
 */
public class ZWShadowConfig {

    public ZWShadowConfig() {
    }

    public static class Builder {
        @ColorInt
        private int mColor;
        @ColorInt
        private int mShadowColor;
        private int[] mGradientColorArray;
        @Nullable
        private float[] mGradientPositions;
        private LinearGradient mLinearGradient;
        private int mRadius;
        private int mShadowRadius;
        private int mOffsetX = 0;
        private int mOffsetY = 0;

        public Builder() {
            this.mColor = cn.davidsu.library.R.color.primary_material_dark;
            this.mShadowColor = cn.davidsu.library.R.color.primary_text_disabled_material_dark;
            this.mRadius = 10;
            this.mShadowRadius = 16;
            this.mOffsetX = 0;
            this.mOffsetY = 0;
        }

        public ZWShadowConfig.Builder setColor(@ColorInt int color) {
            this.mColor = color;
            return this;
        }

        public ZWShadowConfig.Builder setShadowColor(@ColorInt int shadowColor) {
            this.mShadowColor = shadowColor;
            return this;
        }

        public ZWShadowConfig.Builder setGradientColorArray(@Nullable int[] colorArray) {
            this.mGradientColorArray = colorArray;
            return this;
        }

        public ZWShadowConfig.Builder setGradientPositions(@Nullable float[] positions) {
            this.mGradientPositions = positions;
            return this;
        }

        public ZWShadowConfig.Builder setLinearGradient(@Nullable LinearGradient linearGradient) {
            this.mLinearGradient = linearGradient;
            return this;
        }

        public ZWShadowConfig.Builder setRadius(int radius) {
            this.mRadius = radius;
            return this;
        }

        public ZWShadowConfig.Builder setShadowRadius(int shadowRadius) {
            this.mShadowRadius = shadowRadius;
            return this;
        }

        public ZWShadowConfig.Builder setOffsetX(int offsetX) {
            this.mOffsetX = offsetX;
            return this;
        }

        public ZWShadowConfig.Builder setOffsetY(int offsetY) {
            this.mOffsetY = offsetY;
            return this;
        }

        public ZWCustomShadowBackground builder() {
            return new ZWCustomShadowBackground(this.mColor, this.mGradientColorArray, this.mGradientPositions, this.mShadowColor, this.mLinearGradient, this.mRadius, this.mShadowRadius, this.mOffsetX, this.mOffsetY);
        }
    }

}
