package com.reggie.au.autohome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.view.SmtechSceneModeView;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;

import java.util.List;
import java.util.Map;
import java.util.Random;


public class AuActivityHouse extends SmartActivity {

    private Button btnl;
    private Button btnr;
    private Button btn_home;
    private GridView sGridView;
    private final static List<SmtechSceneModeView> modList=SmtechData.modList;//获取情景模式列表
    private static final int[] COLOR = new int[]{0xff33b5e5, 0xffaa66cc, 0xff99cc00, 0xffffbb33, 0xffff4444};
    private AssistTool assistTool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_au_activity_house);
        init();
    }

    private void init(){
        assistTool = new AssistTool(this);
        btnl = (Button) this.findViewById(R.id.btn_l);
        btnr = (Button) this.findViewById(R.id.btn_r);
        sGridView=(GridView) this.findViewById(R.id.grd_list);
        System.out.println("======modList====>"+modList.size());
        if (modList.size()>0){
            for (int i = 0; i < modList.size(); i++) {
                System.out.println(i+"<==========>"+modList.size());
            }

        }

        sGridView.setAdapter(new modAdapter());

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountJump(10, UserInfoActivity.class, false);
            }
        });

        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assistTool.showToast(SmtechData.houseInfo.getHouseUser());
            }
        });

        btn_home = (Button) findViewById(R.id.btn_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AuActivityHouse.this, RoomsListActivity.class);
                startActivity(intent);
            }
        });
    }


    public class modAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return modList.size();
        }

        @Override
        public Object getItem(int position) {
            return modList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return modList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(AuActivityHouse.this);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
                convertView.setLayoutParams(lp);
            }
            TextView view = (TextView) convertView;
            view.setText(modList.get(position).getName());
            view.setBackgroundColor(COLOR[position % 5]);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.WHITE);
            AbsListView.LayoutParams lp = (AbsListView.LayoutParams) view.getLayoutParams();
            lp.width = 300;
            lp.height = (int) (getPositionRatio(position) * 200);
            view.setLayoutParams(lp);
            return view;

        }

        private final Random mRandom = new Random();
        private final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
        private double getPositionRatio(final int position) {
            double ratio = sPositionHeightRatios.get(position, 0.0);
            if (ratio == 0) {
                ratio = getRandomHeightRatio();
                sPositionHeightRatios.append(position, ratio);
            }
            return ratio;
        }

        private double getRandomHeightRatio() {
            return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
        }
    }



}
