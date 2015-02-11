package com.reggie.au.autohome;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;


public class AuActivityHouse extends SmartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_au_activity_house);


    }



}
