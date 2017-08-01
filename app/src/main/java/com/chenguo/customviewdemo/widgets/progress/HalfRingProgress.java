package com.chenguo.customviewdemo.widgets.progress;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.chenguo.customviewdemo.R;

/**
 * @Description: 半圆环形进度条
 * @author: chenguoguo
 * @date: 2017/7/26 上午10:22
 * ***********************
 *
 */
public class HalfRingProgress extends View {

    private Paint outPaint;
    private Paint inPaint;
    private Paint textPaint;
    private Paint progressPaint;

    private RectF outRectF;
    private RectF inRectF;

//    private float mRadius;

//    private String text = "";
//    private float progress = 0;
//    private boolean isTextAnim = false;

    private int ringBackground;
    private float width = 240;
    private float height = 130;
    private float radius;
    private int progress_color;
    private float textSize;
    private String text = "";
    private float textMarginBottom;
    private boolean isStartProgressAnim;
    private boolean isStartTextAnim;
    private float progress = 0;
    private float ringWidth;

    public HalfRingProgress(Context context) {
        this(context, null);
    }

    public HalfRingProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HalfRingProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HalfRingProgress,0,0);
        ringBackground = typeArray.getColor(R.styleable.HalfRingProgress_ring_background,Color.GRAY);
        width = typeArray.getDimension(R.styleable.HalfRingProgress_width,240);
        height = typeArray.getDimension(R.styleable.HalfRingProgress_height,130);
        radius = typeArray.getDimension(R.styleable.HalfRingProgress_radius,120);
        progress_color = typeArray.getColor(R.styleable.HalfRingProgress_progress_color,Color.GREEN);
        textSize = typeArray.getDimension(R.styleable.HalfRingProgress_textSize,26);
        text = typeArray.getString(R.styleable.HalfRingProgress_text);
        if(null == text)
            text = "";
        textMarginBottom = typeArray.getDimension(R.styleable.HalfRingProgress_text_margin_bottom,5);
        isStartProgressAnim = typeArray.getBoolean(R.styleable.HalfRingProgress_isStartProgressAnim,true);
        isStartTextAnim = typeArray.getBoolean(R.styleable.HalfRingProgress_isStartTextAnim,false);
        progress = typeArray.getFloat(R.styleable.HalfRingProgress_progress,0);
        ringWidth = typeArray.getDimension(R.styleable.HalfRingProgress_ringWidth,34);

    }

    private void initVariable() {
        outPaint = new Paint();
        inPaint = new Paint();
        textPaint = new Paint();
        progressPaint = new Paint();

        outPaint.setAntiAlias(true);
        outPaint.setColor(progress_color);
        outPaint.setStrokeCap(Paint.Cap.ROUND);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeWidth(ringWidth);

        inPaint.setAntiAlias(true);
        inPaint.setColor(Color.GRAY);
        inPaint.setStrokeCap(Paint.Cap.ROUND);
        inPaint.setStyle(Paint.Style.STROKE);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);

        progressPaint.setAntiAlias(true);
        progressPaint.setColor(ringBackground);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(ringWidth);

//        inRectF = new RectF(80, 80, 320, 320);
//        outRectF = new RectF(60, 60, 340, 340);

        inRectF = new RectF(80, 80, 320, 320);
        outRectF = new RectF(ringWidth, ringWidth, radius*2+20, radius*2+20);

//        radius = outRectF.right - outRectF.left;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 这里要计算一下控件的实际大小，然后调用setMeasuredDimension来设置
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);   //获取宽的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); //获取高的模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   //获取宽的尺寸
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); //获取高的尺寸
        Log.v("openxu", "宽的模式:"+widthMode);
        Log.v("openxu", "高的模式:"+heightMode);
        Log.v("openxu", "宽的尺寸:"+widthSize);
        Log.v("openxu", "高的尺寸:"+heightSize);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY) {
            //如果match_parent或者具体的值，直接赋值
            width = widthSize;
        } else {
            //如果是wrap_content，我们要得到控件需要多大的尺寸
            float textWidth = outRectF.width();   //文本的宽度
            //控件的宽度就是文本的宽度加上两边的内边距。内边距就是padding值，在构造方法执行完就被赋值
//            width = (int) (getPaddingLeft() + textWidth + getPaddingRight()+ringWidth*2);
            width = (int) (textWidth + ringWidth*2);
            Log.v("openxu", "文本的宽度:"+textWidth + "控件的宽度："+width);
        }
        //高度跟宽度处理方式一样
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float textHeight = outRectF.height();
//            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
//            height = (int) (getPaddingTop() + textHeight/2+getPaddingBottom() + ringWidth*2);
            height = (int) (textHeight/2+ ringWidth*2);
            Log.v("openxu", "文本的高度:"+textHeight + "控件的高度："+height);
        }
        //保存测量宽度和测量高度
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(outRectF, 0, -180, false, progressPaint);
        canvas.drawArc(outRectF, -180, progress, false, outPaint);
//        canvas.drawArc(inRectF,0,-180,false,inPaint);
//        onCenterTextDraw.onCenterTextCallback(canvas,text,
//                60+radius/2-textPaint.measureText(text)/2,60+radius/2-20,progress,textPaint);
        canvas.drawText(text, ringWidth + radius - textPaint.measureText(text) / 2, outRectF.top + radius - textMarginBottom, textPaint);

    }

    /**
     * 设置显示文字
     *
     * @param proprotion
     */
    public void setText(float proprotion) {
        this.text = "占比" + proprotion + "%";
    }

    /**
     * 设置文字显示是否开启动画
     *
     * @param isAnim
     * @return
     */
    public void setIsTextStartAnim(boolean isAnim) {
        this.isStartTextAnim = isAnim;
    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        this.progress = progress / 180 * 100;
    }

    /**
     * 获取当前进度
     *
     * @return
     */
    public float getProgress() {
        return progress;
    }

    /**
     * 添加动画进度
     *
     * @param progress 进度
     * @param activity
     * @param isAnim   是否开启动画显示
     */
    public void addProrgress(int progress, Activity activity, boolean isAnim) {
        if (isAnim) {
            Thread thread = new Thread(new ProgressThread(progress, activity));
            thread.start();
        } else {
            setProgress(progress);
        }
    }

    class ProgressThread implements Runnable {
        int i = 0;
        private int progress;
        private Activity activity;

        public ProgressThread(int progress, Activity activity) {
            this.progress = progress;
            this.activity = activity;
        }

        @Override
        public void run() {
            for (; i <= progress; i++) {
                if (activity.isFinishing()) {
                    break;
                }
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = i;
                SystemClock.sleep(8);
                mHandler.sendMessage(msg);
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int i = msg.arg1;
                    setProgress(i);
                    if (isStartTextAnim) {
                        setText(i);
                    }
                    invalidate();
                    break;
            }
        }
    };

    public interface OnCenterTextDraw {
        void onCenterTextCallback(Canvas canvas, String descrip, float x, float y, float progress, Paint textPaint);
    }

    private OnCenterTextDraw onCenterTextDraw;

    public void setOnCenterTextDraw(OnCenterTextDraw onCenterTExtDraw) {
        this.onCenterTextDraw = onCenterTExtDraw;
    }
}
