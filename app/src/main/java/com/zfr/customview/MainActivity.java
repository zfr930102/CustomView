package com.zfr.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.zfr.customview.utils.DensityUtils;
import com.zfr.customview.weight.ArcGradeView;

public class MainActivity extends AppCompatActivity {

    private ArcGradeView agv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityUtils.setCustomDensity(this, this.getApplication());
        setContentView(R.layout.activity_main);
        agv = findViewById(R.id.agv);
//        agv.startAnimator(0, 90);
        agv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         for (int i = 1; i <= 100; i++) {
                             agv.setProcess((float) (i * 0.01));
                             SystemClock.sleep(500);
                         }
                     }
                 }).start();
            }
        });
    }
}
