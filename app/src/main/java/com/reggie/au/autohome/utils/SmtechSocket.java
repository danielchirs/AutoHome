package com.reggie.au.autohome.utils;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by daniel on 2015/3/30.
 */
public class SmtechSocket {

    ServerSocket sever;

    public SmtechSocket(){
        try{
            sever = new ServerSocket(port);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
