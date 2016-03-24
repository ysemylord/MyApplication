package com.example.sijia.myapplication.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sijia.myapplication.ContainerActivity;
import com.example.sijia.myapplication.FormatAdapter.SimpleListViewBaseAdapter;
import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.CustomWidget.CustomWidgetList;
import com.example.sijia.myapplication.fragment.WidgetUse.RadioButtonFragment;
import com.example.sijia.myapplication.fragment.WidgetUse.RatingBarFragment;
import com.example.sijia.myapplication.fragment.WidgetUse.SeekBarFragment;
import com.example.sijia.myapplication.fragment.WidgetUse.WebViewFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    @Bind(R.id.fragment_list)
    ListView mFragmentListView;

    List<String> mNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initSystemBar((Activity)Main2Activity.this);
        ButterKnife.bind(this);
        mNames=new ArrayList<>();
        mNames.add("RatingBar");
        mNames.add("SeekBar");
        mNames.add("RadioButton");
        mNames.add("WebView");
        mNames.add("CustomWidgetList 自定义控件");
        ShowFragnemtnAdapter showFragnemtnAdapter=new ShowFragnemtnAdapter(this,mNames);
        mFragmentListView.setAdapter(showFragnemtnAdapter);
        mFragmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Main2Activity.this,ContainerActivity.class);

                if(position==0){
                    intent.putExtra("Fragment", new RatingBarFragment());
                }else if(position==1){
                    intent.putExtra("Fragment", new SeekBarFragment());
                }else if(position==2){
                    intent.putExtra("Fragment", new RadioButtonFragment());
                }else if(position==3){
                    intent.putExtra("Fragment", new WebViewFragment());
                }else if(position==4){
                    intent.putExtra("Fragment", new CustomWidgetList());
                }

                startActivity(intent);
            }
        });
    }
    public static void initSystemBar(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(activity, true);

        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        tintManager.setStatusBarTintEnabled(true);

        // 使用颜色资源

        tintManager.setStatusBarTintResource(R.color.cube_holo_red_dark);

    }

    @TargetApi(19)

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);

    }

}

class ShowFragnemtnAdapter extends SimpleListViewBaseAdapter<String> {

    public ShowFragnemtnAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {
        TextView textView = holder.getView(android.R.id.text1);
        textView.setText(getItem(position));
        return convertView;
    }

    @Override
    public int getResourece() {
        return android.R.layout.simple_list_item_1;
    }


}
