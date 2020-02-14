package com.zfr.customview.weight;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.zfr.customview.R;

/**
 * 圆弧进度(进度条功能)
 */
public class ArcGradeView extends View {

    private int arcBackColor;
    private int arcGradeColor;
    private int arcBackPaintSize;
    private float arcRectFLeft;
    private float arcRectFTop;
    //距离右侧的距离
    private float arcRectFRight;
    //距离底部的距离
    private float arcRectFBottom;
    private Paint mPaint;
    private int viewWidth;
    private int viewHeight;
    private int arcStartAngle;
    private int arcEndAngle;
    private RectF rectF;
    private int currentAngle;

    public ArcGradeView(Context context) {
        this(context,null);
    }

    public ArcGradeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ArcGradeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs,defStyleAttr);
    }

    /**
     * 初始化相关数据
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs,int defStyleAttr) {
        //获取自定义的相关属性
        if (attrs == null) {
            arcBackColor = Color.parseColor("#666666");
            arcGradeColor = Color.parseColor("#FF4A40");
            arcBackPaintSize = 20;
            arcRectFLeft = 20;
            arcRectFTop = 20;
            arcRectFRight = 20;
            arcRectFBottom = 20;
        } else {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                    R.styleable.ArcGradeViewStyle, defStyleAttr, 0);
            arcBackColor = typedArray.getColor(R.styleable.ArcGradeViewStyle_arc_back_color,
                    Color.parseColor("#666666"));
            arcGradeColor = typedArray.getColor(R.styleable.ArcGradeViewStyle_arc_grade_color,
                    Color.parseColor("#FF4A40"));
            arcBackPaintSize = typedArray.getInteger(R.styleable.ArcGradeViewStyle_arc_back_paint_size, 20);
            arcRectFLeft = typedArray.getDimension(R.styleable.ArcGradeViewStyle_arc_rectF_left, 20);
            arcRectFTop = typedArray.getDimension(R.styleable.ArcGradeViewStyle_arc_rectF_top, 20);
            arcRectFRight = typedArray.getDimension(R.styleable.ArcGradeViewStyle_arc_rectF_left, 20);
            arcRectFBottom = typedArray.getDimension(R.styleable.ArcGradeViewStyle_arc_rectF_left, 20);
            arcStartAngle = typedArray.getInteger(R.styleable.ArcGradeViewStyle_arc_start_angle, 180);
            arcEndAngle = typedArray.getInteger(R.styleable.ArcGradeViewStyle_arc_end_angle, 180);
            typedArray.recycle();
        }

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //设置画笔的宽度
        mPaint.setStrokeWidth(20);
        //设置画出的形状
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置画笔的类型
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackArc(canvas);
        drawGradeArc(canvas);
    }

    private void drawGradeArc(Canvas canvas) {
//当前的角度可以拿到弧度
        double angle = currentAngle * Math.PI / 180;
        Log.e(ArcGradeView.class.getSimpleName(), "当前角度" + currentAngle +"\t转换后的角度" + angle);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        mPaint.setColor(arcGradeColor);
        canvas.drawArc(rectF, arcStartAngle, currentAngle, false, mPaint);
        canvas.drawCircle((float) (rectF.centerX() - cos * (rectF.centerX() - arcRectFLeft)),
                (float) (rectF.centerY() - sin * (rectF.centerY() - arcRectFTop)), 10, mPaint);
    }

    private void drawBackArc(Canvas canvas) {
        mPaint.setColor(arcBackColor);
        rectF = new RectF(arcRectFLeft, arcRectFTop, viewWidth - arcRectFRight, viewHeight - arcRectFBottom);
        canvas.drawArc(rectF, arcStartAngle, arcEndAngle, false, mPaint);
    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(int currentAngle) {
        this.currentAngle = currentAngle;
        postInvalidate();
    }

    public void startAnimator(int startAngle, int endAngle) {
        ObjectAnimator currentAngle = ObjectAnimator.ofInt(this, "currentAngle", startAngle, endAngle);
        currentAngle.setDuration(10000);
        currentAngle.setStartDelay(1000);
        currentAngle.start();
        /*ValueAnimator valueAnimator = ValueAnimator.ofInt(startAngle, endAngle);
        valueAnimator.setDuration(10000);
        valueAnimator.setStartDelay(1000);
//        valueAnimator.setRepeatCount(1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                setCurrentAngle(currentValue);
            }
        });
        valueAnimator.start();*/
    }
}
