package com.reggie.au.autohome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.reggie.au.autohome.command.Configuration;
import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.model.SmtechHouse;
import com.reggie.au.autohome.utils.DomXml;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.appInfo.AppInfo;
import com.tandong.sa.sql.ActiveAndroid;
import com.tandong.sa.sql.query.Select;
import com.tandong.sa.system.SystemInfo;
import com.tandong.sa.tools.AssistTool;

import java.util.List;


public class WelcomActivity extends SmartActivity {

    private AssistTool assistTool;
    private AppInfo appInfo;
    private TextView txtip;
    private TextView txtnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        assistTool = new AssistTool(this);
        appInfo=new AppInfo(this);
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
        assistTool.savePreferenceLong(Configuration.app_lasttime, appInfo.getLastUpdateTime(this));//记录每次最后登录时间
        long sequece=assistTool.getPreferenceLong(Configuration.app_sequence);
        assistTool.savePreferenceLong(Configuration.app_sequence,sequece++);//使用次数
        boolean house_data_fg = assistTool.FileExists(Environment.getExternalStorageDirectory()+"/smtechcache/house_data.xml");
        boolean house_mode_fg = assistTool.FileExists(Environment.getExternalStorageDirectory()+"/smtechcache/house_mode.xml");
        if(!house_data_fg){
            txtnet.setText("配置家居数据文件不存在,请导入数据");
        }else {
            if (!house_mode_fg) {
                txtnet.setText("配置家居情景文件不存在,请导入数据");
            } else {
                try {
                    DomXml.Personxml(Environment.getExternalStorageDirectory() + "/smtechcache/house_data.xml", 0);

                    System.out.println("=======2222222222=======>"+ SmtechData.houseList.size());

                } catch (Exception e) {
                    e.printStackTrace();
                }


                CountJump(10000, AuActivityHouse.class, true);
            }
        }
    }





    //处理初始数据
    private void initdata(){
//        ActiveAndroid.beginTransaction();
//        try {
//            for (int i = 0; i < 1; i++) {
//                SmtechHouse house=new SmtechHouse("测试房屋"+i,"测试使用地址"+i,"a0u3001"+i);
//                house.save();
//                house=null;
//            }
//            ActiveAndroid.setTransactionSuccessful();
//        }catch (Exception e){
//
//        }finally {
//            ActiveAndroid.endTransaction();
//        }

//        System.out.println("=====33333333======>");
//        List<SmtechHouse> list=new Select().from(SmtechHouse.class).execute();
//        System.out.println("=====55555555======>"+list.size());
    }


}
