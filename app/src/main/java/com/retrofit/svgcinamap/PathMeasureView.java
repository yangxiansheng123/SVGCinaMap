package com.retrofit.svgcinamap;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/20.
 */

public class PathMeasureView extends View {
    private Path path,dst;
    private Paint mPaint;
    private float length;
    private PathMeasure pathMeasure;
    private float mAnimatorValue;
    private float[] pos = new float[2];

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pathMeasure = new PathMeasure();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        path = new Path();
        path.addCircle(400, 400, 100, Path.Direction.CW);
        pathMeasure.setPath(path, true);
        length = pathMeasure.getLength();
        dst = new Path();
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dst.reset();
        // 硬件加速的BUG
        dst.lineTo(0,0);
        float stop = length * mAnimatorValue;
        // 从path中截取指定长度的片段到dst中。
        pathMeasure.getSegment(0, stop, dst, true);
        //获取指定长度位置上的坐标值
        pathMeasure.getPosTan(stop,pos,null);
        canvas.drawPath(dst, mPaint);
        canvas.drawCircle(pos[0],pos[1],25,mPaint);
    }
}
