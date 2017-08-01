package com.chenguo.customviewdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenguo.customviewdemo.widgets.progress.HalfRingProgress;

public class MainActivity extends AppCompatActivity {

    private HalfRingProgress halfRingProgress;
    private ImageView t
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        halfRingProgress = (HalfRingProgress) findViewById(R.id.halfRingprogress);
        halfRingProgress.setText(60);
        halfRingProgress.setIsTextStartAnim(false);
//        halfRingProgress.setProgress(60.5f);
        halfRingProgress.setOnCenterTextDraw(new HalfRingProgress.OnCenterTextDraw() {
            @Override
            public void onCenterTextCallback(Canvas canvas, String descrip,float x,float y, float progress,Paint textPaint) {
                String description = "占比"+progress+"%";
                canvas.drawText(description,x,y,textPaint);
            }
        });
        halfRingProgress.addProrgress(120,this,true);
    }
}
