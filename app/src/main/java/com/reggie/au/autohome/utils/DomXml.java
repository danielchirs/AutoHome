package com.reggie.au.autohome.utils;

import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.view.SmtechDeviceView;
import com.reggie.au.autohome.view.SmtechHouseView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by daniel on 2015/3/26.
 * XML文件工具类
 */
public class DomXml {

    //读取XML配置文件全路径
    public static void Personxml(String path,int type)throws Exception {
        if(type==0){//读取数据配置
            String readString=getxml(path);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlParser = factory.newPullParser();
            xmlParser.setInput(new StringReader(readString));
            int evtType= xmlParser.getEventType();
            SmtechHouseView smtechHouseView=null;
            SmtechDeviceView smtechDeviceView=null;
            while(evtType!=XmlPullParser.END_DOCUMENT){
                switch(evtType){
                    case XmlPullParser.START_TAG:
                        String name=xmlParser.getName();
                        if("room".equals(name)){
                            smtechHouseView=new SmtechHouseView();
                            smtechHouseView.rid=Integer.parseInt(xmlParser.getAttributeValue(0));
                            smtechHouseView.name=xmlParser.getAttributeValue(1);
                        }
                        if("device".equals(name)){
                            smtechDeviceView=new SmtechDeviceView();
                        }
                        if("name".equals(name)){
                            smtechDeviceView.name=xmlParser.nextText();
                        }
                        if("type".equals(name)){
                            smtechDeviceView.type=Integer.parseInt(xmlParser.nextText());
                        }
                        if("machinecode".equals(name)){
                            smtechDeviceView.machinecode=xmlParser.nextText();
                        }
                        if("layout".equals(name)){
                            smtechDeviceView.layoutid=Integer.parseInt(xmlParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG://结束元素事件
                        if (xmlParser.getName().equalsIgnoreCase("device"))
                        {
                            smtechHouseView.deviceList.add(smtechDeviceView);
                            smtechDeviceView=null;
                        }
                        if (xmlParser.getName().equalsIgnoreCase("room"))
                        {
                            SmtechData.houseList.add(smtechHouseView);
                            smtechHouseView=null;
                        }
                        break;
                    default:
                        break;
                }
                //如果xml没有结束，则导航到下一个节点
                evtType=xmlParser.next();
            }
        }else if(type==1){//读取情景模式


        }

    }


    public static String getxml(String pathall) throws IOException {
        File file=new File(pathall);
        FileInputStream fileIS = new FileInputStream(file);
        StringBuffer sb=new StringBuffer();
        BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
        String readString = new String();
        while((readString = buf.readLine())!= null){
            sb.append(readString);
        }
        return sb.toString();
    }



}
