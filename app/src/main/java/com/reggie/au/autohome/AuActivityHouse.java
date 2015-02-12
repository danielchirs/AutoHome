package com.reggie.au.autohome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;


public class AuActivityHouse extends SmartActivity {

    private Button btnl;
    private Button btnr;
    private AssistTool assistTool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_au_activity_house);
        assistTool = new AssistTool(this);
        btnl=(Button)this.findViewById(R.id.btn_l);
        btnr=(Button)this.findViewById(R.id.btn_r);

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assistTool.showToast("用户信息");
            }
        });

        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assistTool.showToast("功能按键");
            }
        });


    }









}
