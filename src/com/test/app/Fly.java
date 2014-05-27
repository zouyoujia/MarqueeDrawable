package com.test.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

/**
 * Created by youjia.zyj on 2014/5/27.
 */
public class Fly extends Drawable implements Runnable {
    public static final int MOVE_PIX_PER_FRAME = 5;
    public static final int FRAME_RATE = 25; //million second
    Bitmap mFlyMap;
    Matrix mMatrix;
    Paint mPaint;
    int nextPix = 0;
    boolean isRun = false;

    public Fly(Bitmap flyMap) {
        mFlyMap = flyMap;
        mPaint = new Paint();
        mMatrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas) {
        float scale = ((float)canvas.getHeight()) / mFlyMap.getHeight();
        float left = nextPix;
        float right = left + canvas.getWidth()/scale;
        if (right > mFlyMap.getWidth()) {
            mMatrix.setRectToRect(
                    new RectF(0, 0, right - mFlyMap.getWidth(), canvas.getHeight()/scale),
                    new RectF(canvas.getWidth() - (right - mFlyMap.getWidth()) * scale, 0, canvas.getWidth(), canvas.getHeight()),
                    Matrix.ScaleToFit.START
            );
            canvas.drawBitmap(mFlyMap, mMatrix, mPaint);
        }
        mMatrix.setRectToRect(
                new RectF(left, 0, Math.min(right, mFlyMap.getWidth()), mFlyMap.getHeight()),
                new RectF(0, 0, canvas.getWidth(), canvas.getHeight()),
                Matrix.ScaleToFit.START);
        canvas.drawBitmap(mFlyMap, mMatrix, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    public void start() {
        if (!isRun) {
            isRun = true;
            run();
        }
    }

    private void nextFrame() {
        nextPix += MOVE_PIX_PER_FRAME;
        if (nextPix >= mFlyMap.getWidth()) {
            nextPix = 0;
        }
        invalidateSelf();
    }

    @Override
    public void run() {
        nextFrame();
        scheduleSelf(this, SystemClock.uptimeMillis() + FRAME_RATE);
    }
}
