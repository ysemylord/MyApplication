package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class DrawerLayoutDemoActivity extends Activity {
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout_demo);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(DrawerLayoutDemoActivity.this, "侧滑栏打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(DrawerLayoutDemoActivity.this, "侧滑栏关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //
            }
        });
    }

    public void open(View view) {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void close(View view) {
        mDrawerLayout.closeDrawers();
    }
}
