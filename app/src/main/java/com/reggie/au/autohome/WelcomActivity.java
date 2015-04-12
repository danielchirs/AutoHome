package com.reggie.au.autohome;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.reggie.au.autohome.command.Configuration;
import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.utils.DomXml;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.appInfo.AppInfo;
import com.tandong.sa.system.SystemInfo;
import com.tandong.sa.tools.AssistTool;


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
        appInfo = new AppInfo(this);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_welcom);
        AssistTool at = new AssistTool(this);
        SystemInfo systemInfo = new SystemInfo(this);
        String NetWorkType = systemInfo.getNetWorkType();
        String wifiIp = at.getWifiIp();
        txtip = (TextView) this.findViewById(R.id.txt_ip);
        txtnet = (TextView) this.findViewById(R.id.txt_net);
        txtip.setText(wifiIp);
        txtnet.setText(NetWorkType);
        assistTool.savePreferenceLong(Configuration.app_lasttime, appInfo.getLastUpdateTime(this));//记录每次最后登录时间
        long sequece = assistTool.getPreferenceLong(Configuration.app_sequence);
        assistTool.savePreferenceLong(Configuration.app_sequence, sequece++);//使用次数yg
        boolean house_info_fg = assistTool.FileExists(Environment.getExternalStorageDirectory() + "/smtechcache/house_info.xml");
        boolean house_data_fg = assistTool.FileExists(Environment.getExternalStorageDirectory() + "/smtechcache/house_data.xml");
        boolean house_mode_fg = assistTool.FileExists(Environment.getExternalStorageDirectory() + "/smtechcache/house_mode.xml");
        if (!house_info_fg) {
            txtnet.setText("配置房屋基本信息数据文件不存在,请导入数据");
        } else {
            if (!house_data_fg) {
                txtnet.setText("配置家居数据文件不存在,请导入数据");
            } else {
                if (!house_mode_fg) {
                    txtnet.setText("配置家居情景文件不存在,请导入数据");
                } else {
                    try {
                        DomXml.Personxml(Environment.getExternalStorageDirectory() + "/smtechcache/house_info.xml", 0);//房子的基本信息
                        DomXml.Personxml(Environment.getExternalStorageDirectory() + "/smtechcache/house_data.xml", 1);//房间控制配置
                        DomXml.Personxml(Environment.getExternalStorageDirectory() + "/smtechcache/house_mode.xml", 2);//情景模式控制
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("=======房间数量=======>" + SmtechData.houseList.size());
                    System.out.println("=======情景数据=======>" + SmtechData.modMap.size());
                    System.out.println("=======1111111=======>" + SmtechData.dataMap.size());
                    CountJump(5000, AuActivityHouse.class, true);
                }
            }
        }
        CountJump(1000, AuActivityHouse.class, true);
    }

    //处理初始数据
    private void initdata() {
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
