package com.chenguo.customviewdemo.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @Description:
 * @author: chenguoguo
 * @date: 2017/7/25 上午10:43
 */
public class CustomView extends View {

    private Paint mPaint = new Paint();

    private Paint piePaint;
    private RectF rectF;

    private float[] degress;
    private int[] mColors;

    private float totalDegree;
    private float startAngle = 0;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        //绘制自定义图形
//
//    }

    private void init() {
        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setStyle(Paint.Style.FILL);

        rectF = new RectF(100,100,500,500);
        degress = new float[]{5f,10f,60f,100f,160f,40f,5f,200f,50f};
        mColors = new int[]{0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
                0xFFE6B800, 0xFF7CFC00};

        for(int i = 0; i <degress.length ; i++){
            totalDegree += degress[i];
        }

        Log.e("-----",totalDegree+"");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        for(int i = 0;i<degress.length;i++){
            float sweetAngle = ((degress[i]/totalDegree)) * 360 - 1;
            piePaint.setColor(mColors[i]);
            canvas.drawArc(rectF,startAngle,sweetAngle,true,piePaint);
            startAngle += sweetAngle + 1;
            Log.e("---",sweetAngle +"----"+startAngle);
        }
    }
}
