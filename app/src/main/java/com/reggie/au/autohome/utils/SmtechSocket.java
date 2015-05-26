package com.reggie.au.autohome.utils;

import com.reggie.au.autohome.command.SmtechData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by daniel on 2015/3/30.
 */
public class SmtechSocket {


    public SmtechSocket(){
        try{
            //创建连接
            SmtechData.sever = new Socket();
            SocketAddress socAddress = new InetSocketAddress(SmtechData.houseInfo.getIp(),SmtechData.houseInfo.getPort());
            SmtechData.sever.connect(socAddress,5000);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //发送消息指令
    public String sendmsg(String msg){
        if (SmtechData.sever!=null && SmtechData.sever.isConnected()){
            try {
                BufferedReader in =new BufferedReader(new InputStreamReader(SmtechData.sever.getInputStream()));
                PrintWriter out=new PrintWriter(SmtechData.sever.getOutputStream());
                out.print(msg);
                out.flush();
                return in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                    try {
                        if (SmtechData.sever!=null && SmtechData.sever.isConnected()){
                            SmtechData.sever.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                   }
            }
            return "";
        }
        return "";
    }

}
