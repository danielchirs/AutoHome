package com.reggie.au.autohome;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reggie.au.autohome.command.SmtechData;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;


public class UserInfoActivity extends SmartActivity {

    private TextView txt_hname,txt_name,txt_address,txt_tel,txt_mobile;
    private Button btnl,btnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_user_info);
        init();
    }

    private void init(){
        btnl = (Button) this.findViewById(R.id.btn_l);
        btnl.setText("返回");
        btnr = (Button) this.findViewById(R.id.btn_r);
        ((TextView)this.findViewById(R.id.txt_hname)).setText(SmtechData.houseInfo.getHouseName());
        ((TextView)this.findViewById(R.id.txt_name)).setText(SmtechData.houseInfo.getHouseUser());
        ((TextView)this.findViewById(R.id.txt_address)).setText(SmtechData.houseInfo.getHouseAddress());
        ((TextView)this.findViewById(R.id.txt_tel)).setText(SmtechData.houseInfo.getHouseTel());
        ((TextView)this.findViewById(R.id.txt_mobile)).setText(SmtechData.houseInfo.getHouseMobile());


        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
