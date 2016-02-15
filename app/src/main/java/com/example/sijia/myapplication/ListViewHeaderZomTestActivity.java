
package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sijia.myapplication.widget.PullToZoomListViewDemo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListViewHeaderZomTestActivity extends Activity {

    @Bind(R.id.test_listview)
    PullToZoomListViewDemo mTestListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_header_zom_test);
        ButterKnife.bind(this);

      RelativeLayout re = (RelativeLayout) findViewById(R.id.root_view);
        FrameLayout frameLayout = new FrameLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        frameLayout.addView(imageView);
        re.addView(frameLayout);

        ArrayAdapter mArrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,new String[]{
                "1","2","3","2",  "1","2","3","2",  "1","2","3","2",  "1","2","3","2",  "1","2","3","2",  "1","2","3","2"
        });
        mTestListview.setAdapter(mArrayAdapter);
    }
}

