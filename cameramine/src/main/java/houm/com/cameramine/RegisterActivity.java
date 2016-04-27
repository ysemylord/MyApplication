package houm.com.cameramine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.phone_et)
    EditText mPhoneET;
    @Bind(R.id.pwd_et)
    EditText mPwdEt;
    @Bind(R.id.register_btn)
    Button mRegisterBtn;
    @Bind(R.id.name_et)
    EditText mNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("注册");

        ButterKnife.bind(this);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser avUser = new AVUser();
                avUser.setUsername(mNameEt.getText().toString().trim());
                avUser.setMobilePhoneNumber(mPhoneET.getText().toString().trim());
                avUser.setPassword(mPwdEt.getText().toString().trim());
                avUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            finish();
                            return;
                        }
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
