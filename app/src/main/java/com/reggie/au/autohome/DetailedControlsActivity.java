package com.reggie.au.autohome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.utils.AutohomeWidgetAdapter;
import com.reggie.au.autohome.view.SmtechDeviceView;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;

import java.util.List;


public class DetailedControlsActivity extends SmartActivity {

    // Logging TAG
    private static final String TAG = "WidgetListActivity";

    private AutohomeWidgetAdapter autohomeWidgetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_detailed_controls);
        setView();
    }

    private void setView() {
        TextView control_name_tv = (TextView) findViewById(R.id.control_name_tv);
        Intent intent = getIntent();
        String roomName = intent.getStringExtra("room_name");
        String roomId = intent.getStringExtra("roomId");
        control_name_tv.setText("当前房间：" + roomName);
        Log.i(TAG, "------------------- roomId -------------" + roomId);
        List<SmtechDeviceView> deviceList = SmtechData.dataMap.get(roomId);
        Log.i(TAG, "device count : " + deviceList.size());
        autohomeWidgetAdapter = new AutohomeWidgetAdapter(this, R.layout.activity_detailed_controls, deviceList);
        ListView listView = (ListView) findViewById(R.id.device_listview);
        listView.setAdapter(autohomeWidgetAdapter);
    }

}
