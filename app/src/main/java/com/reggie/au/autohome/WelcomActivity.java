package com.reggie.au.autohome;

import android.os.Bundle;
import android.widget.TextView;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;


public class WelcomActivity extends SmartActivity {

    private TextView txtip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_welcom);
        AssistTool at = new AssistTool(this);
        String wifiIp = at.getWifiIp();
        txtip=(TextView)this.findViewById(R.id.txt_ip);
        txtip.setText(wifiIp);
        CountJump(5000, AuActivityHouse.class, true);
    }
}
