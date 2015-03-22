package com.reggie.au.autohome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.reggie.au.autohome.model.SmtechHouse;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.sql.ActiveAndroid;
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

        SharedPreferences setting = getSharedPreferences("smtech.ini", 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){//第一次
            SmtechHouse house=new SmtechHouse();
            house.code="a0u3001";
            house.address="测试使用地址1";
            house.save();
        }else{
            System.out.println("=====22222222======>");
        }
        CountJump(3000, AuActivityHouse.class, true);
    }


}
