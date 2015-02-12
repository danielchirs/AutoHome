package com.reggie.au.autohome;

import android.os.Bundle;
import android.widget.TextView;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.system.SystemInfo;
import com.tandong.sa.tools.AssistTool;


public class WelcomActivity extends SmartActivity {

    private TextView txtip;
    private TextView txtnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_welcom);
        AssistTool at = new AssistTool(this);
        SystemInfo systemInfo=new SystemInfo(this);
        String NetWorkType = systemInfo.getNetWorkType();
        String wifiIp = at.getWifiIp();
        txtip=(TextView)this.findViewById(R.id.txt_ip);
        txtnet=(TextView)this.findViewById(R.id.txt_net);
        txtip.setText(wifiIp);
        txtnet.setText(NetWorkType);
        CountJump(3000, AuActivityHouse.class, true);
    }
}
