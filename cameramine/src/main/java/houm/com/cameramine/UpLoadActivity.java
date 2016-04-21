package houm.com.cameramine;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpLoadActivity extends BaseActivity {

    @Bind(R.id.choosed_im)
    ImageView mChoosedIm;
    @Bind(R.id.des_et)
    EditText mDesEt;
    @Bind(R.id.push_btn)
    Button mPushBtn;

    File mChooseFile;
    String mUserName;
    private ProgressDialog mProgerProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);
        getSupportActionBar().setTitle("参与活动");
        ButterKnife.bind(this);
        mChooseFile = BaseApplication.getInstance().choosedFile;
        mUserName = BaseApplication.getInstance().getSharePre().getString("user_name", "");
        ImageLoader.getInstance().displayImage("file://" + mChooseFile.getAbsolutePath(), mChoosedIm);
        mProgerProgressDialog=new ProgressDialog(this);
        mPushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dess = mDesEt.getText().toString();
                if (TextUtils.isEmpty(dess)) {
                    Toast.makeText(UpLoadActivity.this, "描述请选择", Toast.LENGTH_LONG).show();
                    return;
                }
                AVObject joiner = new AVObject("act_joiner");
                joiner.put("joiner_activity",AVObject.createWithoutData("activity", BaseApplication.getInstance().choosedActivityObjectId));
                joiner.put("name", mUserName);
                joiner.put("des", dess);

                try {
                    joiner.put("Image",  AVFile.withAbsoluteLocalPath(mChooseFile.getAbsolutePath(), mChooseFile.getAbsolutePath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                mProgerProgressDialog.show();
                joiner.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        mProgerProgressDialog.dismiss();
                        if(e!=null) {
                            Log.i("joiner_res", e.getLocalizedMessage() + "");
                        }
                        finish();
                    }
                });


            }
        });

    }
}
