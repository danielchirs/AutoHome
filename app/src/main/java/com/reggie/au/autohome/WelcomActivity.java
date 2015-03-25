package com.reggie.au.autohome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.reggie.au.autohome.model.SmtechHouse;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.sql.ActiveAndroid;
import com.tandong.sa.sql.query.Select;
import com.tandong.sa.system.SystemInfo;
import com.tandong.sa.tools.AssistTool;

import java.util.List;


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
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < 1; i++) {
                SmtechHouse house=new SmtechHouse("测试房屋"+i,"测试使用地址"+i,"a0u3001"+i);
                house.save();
                house=null;
            }
            ActiveAndroid.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            ActiveAndroid.endTransaction();
        }
        System.out.println("=====33333333======>");
        List<SmtechHouse> list=new Select().from(SmtechHouse.class).execute();
        System.out.println("=====55555555======>"+list.size());

        CountJump(3000, AuActivityHouse.class, true);
    }


}
