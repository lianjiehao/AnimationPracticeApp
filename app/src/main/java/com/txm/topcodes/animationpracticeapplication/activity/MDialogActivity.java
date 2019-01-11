package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;

/**
 * Created by Tangxianming on 2019/1/2.
 *
 */
public class MDialogActivity extends AppCompatActivity {
    ImageView ivLogo;

    public static void start(Context context) {
        Intent starter = new Intent(context, MDialogActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ivLogo = findViewById(R.id.ivLogo);
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //得到这个view左上角的坐标（相对于当前Activity）
                int[] position01 = new int[2];
                ivLogo.getLocationInWindow(position01);
                Log.d("<MainActivity>", "getLocationInWindow:" + position01[0] + "," + position01[1]);
                //得到这个view左上角的坐标（相对于当前Screen）
                int[] position02 = new int[2];
                ivLogo.getLocationOnScreen(position02);
                Log.d("<MainActivity>", "getLocationOnScreen:" + position02[0] + "," + position02[1]);
            }
        });
    }
}
