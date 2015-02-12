package com.reggie.au.autohome;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DetailedControlsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_controls);
        setView();
    }

    private void setView() {
        LinearLayout wenduLL = (LinearLayout) findViewById(R.id.wendu_addSub);
        LinearLayout shiduLL = (LinearLayout) findViewById(R.id.shidu_addSub);

        TextView control_name_tv = (TextView) findViewById(R.id.control_name_tv);
        Intent intent = getIntent();
        String roomName = intent.getStringExtra("room_name");
        control_name_tv.setText("当前房间："+roomName);

        final AddAndSubView addAndSubView1 = new AddAndSubView(DetailedControlsActivity.this, 19);
        final AddAndSubView addAndSubView2 = new AddAndSubView(DetailedControlsActivity.this, 36);

        wenduLL.addView(addAndSubView1);
        shiduLL.addView(addAndSubView2);

    }
}
