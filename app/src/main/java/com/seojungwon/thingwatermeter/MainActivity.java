package com.seojungwon.thingwatermeter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private final String[] navItems = {"로그인", "메인", "사용량", "설정"};

    private ListView lvNavList;

    private FrameLayout flContainer;

    private DrawerLayout dlDrawer;

    private Button btn;

    @Override
    public void onBackPressed() {
        if (dlDrawer.isDrawerOpen(lvNavList)) {
            dlDrawer.closeDrawer(lvNavList);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNavList = (ListView)findViewById(R.id.lv_activity_main_nav_list);
        flContainer = (FrameLayout)findViewById(R.id.fl_activity_main_container);
//        btn = (Button)findViewById(R.id.btn);
//
//        btn.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "open", Toast.LENGTH_SHORT).show();
//                dlDrawer.openDrawer(lvNavList);
//            }
//        });

        dlDrawer = (DrawerLayout)findViewById(R.id.dl_activity_main_drawer);

        lvNavList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        FragmentManager fragmentManager = getFragmentManager();
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            Fragment fr = null;

            switch (position) {
                case 0:
                    fr = new Fragment_main();
                    break;
                case 1:
                    fr = new Fragment_login();
                    break;
                case 2:
                    fr = new Fragment_detail();
                    break;
                case 3:
                    fr = new Fragment_setting();
                    break;
                case 4:
                    break;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_activity_main_container, fr);
            fragmentTransaction.commit();
            dlDrawer.closeDrawer(lvNavList);

        }
    }

}


