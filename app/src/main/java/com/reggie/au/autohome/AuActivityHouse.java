package com.reggie.au.autohome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.reggie.au.autohome.command.SmtechData;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;


public class AuActivityHouse extends SmartActivity {

    private Button btnl;
    private Button btnr;
    private Button btn_home;
    private AssistTool assistTool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_au_activity_house);
        assistTool = new AssistTool(this);
        btnl = (Button) this.findViewById(R.id.btn_l);
        btnr = (Button) this.findViewById(R.id.btn_r);

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assistTool.showToast(SmtechData.houseInfo.getHouseName());
            }
        });

        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assistTool.showToast(SmtechData.houseInfo.getHouseUser());
            }
        });

        btn_home = (Button) findViewById(R.id.btn_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AuActivityHouse.this, RoomsListActivity.class);
                startActivity(intent);
            }
        });
    }


}
