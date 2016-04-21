package houm.com.cameramine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.lorentzos.swipecards.CardAdapter;
import com.lorentzos.swipecards.CardMode;

import java.util.ArrayList;
import java.util.List;

import houm.com.cameramine.bean.MyCardBean;
import houm.com.cameramine.util.Util;

public class VoteActivity extends Activity {

    private ArrayList<CardMode> al;
    private CardAdapter adapter;
    private SwipeFlingAdapterView flingContainer;
    private ImageView left, right;
    //  private ImageView finishIM;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        Util.initSystemBar(this);
        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);
        // finishIM= (ImageView) findViewById(R.id.finish_im);
        mContainer = (FrameLayout) findViewById(R.id.container);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                left();
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                right();
            }
        });
        al = new ArrayList<>();


        String[] joinerStrs = getIntent().getStringArrayExtra(ActivityDetailActivity.TO_VOTE);
        for (String joinerStr : joinerStrs) {
            try {
                AVObject joiner = (AVObject) AVObject.parseAVObject(joinerStr);
                String url = joiner.getAVFile("Image").getUrl();
                List images = new ArrayList<>();
                images.add(url);
                al.add(new MyCardBean(joiner.getString("name"), joiner.getInt("voteNu"), images, joiner));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        adapter = new CardAdapter(this, al);
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

                al.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                makeToast(VoteActivity.this, "不喜欢");
                MyCardBean cardBean = (MyCardBean) dataObject;
                AVObject jonerAvObject = cardBean.getJoinerAVObj();
                jonerAvObject.put("lookNum", jonerAvObject.getInt("lookNum") + 1);

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(VoteActivity.this, "喜欢");
                MyCardBean cardBean = (MyCardBean) dataObject;
                AVObject joinerAvObject = cardBean.getJoinerAVObj();
                joinerAvObject.put("voteNu", joinerAvObject.getInt("voteNu") + 1);
                joinerAvObject.put("lookNum", joinerAvObject.getInt("lookNum") + 1);
                joinerAvObject.saveInBackground();


            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                for (int i = 0; i < mContainer.getChildCount(); i++) {
                    mContainer.getChildAt(i).setVisibility(View.GONE);
                }
                mContainer.setBackgroundResource(R.drawable.finish);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1500);
                Log.i("onAdapterAboutToEmpty", "onAdapterAboutToEmpty");
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                try {
                    View view = flingContainer.getSelectedView();
                    view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                    view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(VoteActivity.this, "点击图片");
            }
        });

    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    public void right() {
        flingContainer.getTopCardListener().selectRight();
    }

    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }

    //增加观看数量
    private void addLookNum(AVObject jonerAvObject) {
        jonerAvObject.put("lookNum", jonerAvObject.getInt("lookNum") + 1);
        jonerAvObject.saveInBackground();
    }

}
