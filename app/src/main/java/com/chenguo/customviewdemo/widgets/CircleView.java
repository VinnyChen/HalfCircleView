package com.chenguo.customviewdemo.widgets;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:
 * @author: chenguoguo
 * @date: 2017/7/25 上午9:22
 */
public class CircleView extends View {

    private Paint mPaint;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置绘制模式 FILL:填充模式 STROKE:描边模式 FILL_AND_STROKE:填充并描边
        mPaint.setStrokeWidth(20);//设置线条的宽度，单位像素
        mPaint.setAntiAlias(true);//设置是否抗锯齿，true则边缘光滑，false则边缘锯齿化
        mPaint.setColor(Color.WHITE);//设置绘制内容的颜色
        canvas.drawColor(Color.BLUE);

        //绘制圆
//        canvas.drawCircle(300,300,100,mPaint1);


        //绘制矩形
        //方式一：直接绘制
//        canvas.drawRect(100,100,400,300,mPaint);
        //方式二：使用重载方法绘制
//        Rect rect = new Rect(100,100,400,200);
//        canvas.drawRect(rect,mPaint);
        //方式三：使用重载方法
//        RectF rectF = new RectF(100,100,400,400);
//        canvas.drawRect(rectF,mPaint);


        //绘制点
//        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置点的形状 ROUND 圆形 SQUARE和BUTT 方形
//        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置线条断点的形状 ROUND 圆头 SQUARE 尖头 BUTT 平头
//        canvas.drawPoint(100,100,mPaint);

        //绘制一组点
//        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        //参数1：点坐标数组，参数2：跳过前面几个数字，参数3：从跳过的数字后开始绘制几个数字，此处为8即4个点
        //塞选绘制
//        canvas.drawPoints(points,
//                /*跳过前面两个数字，即两个0*/2,
//                /*从跳过的数字后开始算，总共8个数字，即绘制4个点*/8,
//                mPaint);
        //全部绘制
//        canvas.drawPoints(points,mPaint);


        //绘制椭圆
//        canvas.drawOval(100,100,300,200,mPaint);


        //绘制线
//        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置线条断点的形状 ROUND 圆头 SQUARE 尖头 BUTT 平头
//        canvas.drawLine(100,100,300,500,mPaint);
        //塞选绘制
//        float[] points = {50, 100, 150, 350, 200, 60, 120, 50, 100, 10, 180, 50, 20, 100};
//        canvas.drawLines(points,2,8,mPaint);
        //全部绘制
//        canvas.drawLines(points,mPaint);


        //绘制圆角矩形
        //方式一：
        //参数1、2、3、4分别为left、top、right、bottom，参数5为圆角横向半径，参数6为纵向半径
//        canvas.drawRoundRect(100,100,600,300,10,40,mPaint);
        //方式二:
//        RectF rectF = new RectF(100,100,600,300);
//        canvas.drawRoundRect(rectF,10,10,mPaint);


        //绘制弧形或扇形
//        mPaint.setStyle(Paint.Style.FILL); // 填充模式
//        canvas.drawArc(200, 100, 400, 500, -110, 100, true, mPaint); // 绘制扇形(椭圆两个圆心)
//        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
//        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
//        canvas.drawArc(200, 100, 600, 500, 180, 60, false, mPaint); // 绘制不封口的弧形
    }
}
