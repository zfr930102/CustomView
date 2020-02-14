package com.zfr.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zfr.customview.weight.ArcGradeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArcGradeView agv = findViewById(R.id.agv);
        agv.startAnimator(0, 90);
    }
}
