package com.reggie.au.autohome.utils;

import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.view.SmtechDeviceView;
import com.reggie.au.autohome.view.SmtechHouseInfoView;
import com.reggie.au.autohome.view.SmtechHouseView;
import com.reggie.au.autohome.view.SmtechSceneModeView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by daniel on 2015/3/26.
 * XML文件工具类
 */
public class DomXml {

    //读取XML配置文件全路径
    public static void Personxml(String path,int type)throws Exception {
        if(type==0){//房子基本信息
            analyInfo(path);
        }else if (type==1){//房子控制配置
            analyData(path);
        }else if (type==2){//房子情景配置
            analyMod(path);
        }
    }

    /**
     * 解析房子基础信息
     * @param path
     * @throws Exception
     */
    public static void analyInfo(String path) throws Exception {
        String readString=getxml(path);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xmlParser = factory.newPullParser();
        xmlParser.setInput(new StringReader(readString));
        int evtType= xmlParser.getEventType();
        SmtechHouseInfoView houseInfoView = null;
        while(evtType!=XmlPullParser.END_DOCUMENT){
            switch(evtType){
                case XmlPullParser.START_TAG:
                    String name=xmlParser.getName();
                    if("house".equals(name)){
                        houseInfoView=new SmtechHouseInfoView();
                    }
                    if("housename".equals(name)){
                        houseInfoView.setHouseName(xmlParser.nextText());
                    }
                    if("user".equals(name)){
                        houseInfoView.setHouseUser(xmlParser.nextText());
                    }
                    if("address".equals(name)){
                        houseInfoView.setHouseAddress(xmlParser.nextText());
                    }
                    if("tel".equals(name)){
                        houseInfoView.setHouseTel(xmlParser.nextText());
                    }
                    if("mobile".equals(name)){
                        houseInfoView.setHouseMobile(xmlParser.nextText());
                    }

                    if("ip".equals(name)){
                        houseInfoView.setIp(xmlParser.nextText());
                    }
                    if("domain".equals(name)){
                        houseInfoView.setDomain(xmlParser.nextText());
                    }
                    if("prot".equals(name)){
                        houseInfoView.setPort(Integer.parseInt(xmlParser.nextText()));
                    }
                    if("name".equals(name)){
                        houseInfoView.setLoginName(xmlParser.nextText());
                    }
                    if("password".equals(name)){
                        houseInfoView.setPassword(xmlParser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG://结束元素事件
                    if (xmlParser.getName().equalsIgnoreCase("house"))
                    {
                        SmtechData.houseInfo=houseInfoView;
                    }
                    break;
                default:
                    break;
            }
            //如果xml没有结束，则导航到下一个节点
            evtType=xmlParser.next();
        }

    }

    /**
     * 解析房子指令配置数据
     * @param path
     * @throws Exception
     */
    public static void analyData(String path) throws Exception {
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
                        if("icon".equals(name)){
                            smtechDeviceView.icon=xmlParser.nextText();
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
                            SmtechData.houseMap.put(smtechHouseView.rid+"",smtechHouseView);
                            smtechHouseView=null;
                        }
                        break;
                    default:
                        break;
                }
                //如果xml没有结束，则导航到下一个节点
                evtType=xmlParser.next();
            }
    }

    /**
     * 解析房子情景模式
     * @param path
     * @throws Exception
     */
    public static void analyMod(String path) throws Exception {
        String readString=getxml(path);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xmlParser = factory.newPullParser();
        xmlParser.setInput(new StringReader(readString));
        int evtType= xmlParser.getEventType();
        SmtechSceneModeView smtechSceneModeView=null;
        while(evtType!=XmlPullParser.END_DOCUMENT){
            switch(evtType){
                case XmlPullParser.START_TAG:
                    String name=xmlParser.getName();
                    if("mode".equals(name)){
                        smtechSceneModeView=new SmtechSceneModeView();
                    }
                    if("id".equals(name)){
                        smtechSceneModeView.setId(Integer.parseInt(xmlParser.nextText()));
                    }
                    if("name".equals(name)){
                        smtechSceneModeView.setName(xmlParser.nextText());
                    }
                    if("icon".equals(name)){
                        smtechSceneModeView.setIcon(xmlParser.nextText());
                    }
                    if("machinecode".equals(name)){
                        smtechSceneModeView.setMachinecode(xmlParser.nextText());
                    }
                    if("function".equals(name)){
                        smtechSceneModeView.setFunction(xmlParser.nextText());
                    }
                    if("address".equals(name)){
                        smtechSceneModeView.setAddress(xmlParser.nextText());
                    }
                    if("item".equals(name)){
                        smtechSceneModeView.getItem().put(xmlParser.getAttributeValue(0),xmlParser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG://结束元素事件
                    if (xmlParser.getName().equalsIgnoreCase("mode"))
                    {
                        SmtechData.sceneMap.put(smtechSceneModeView.getId()+"",smtechSceneModeView);
                        smtechSceneModeView=null;
                    }
                    break;
                default:
                    break;
            }
            //如果xml没有结束，则导航到下一个节点
            evtType=xmlParser.next();
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
