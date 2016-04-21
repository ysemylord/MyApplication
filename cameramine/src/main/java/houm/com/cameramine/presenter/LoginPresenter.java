package houm.com.cameramine.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import houm.com.cameramine.BaseApplication;
import houm.com.cameramine.contact.LoginContact;

/**
 * Created by Administrator on 2016//18.
 */
public class LoginPresenter implements LoginContact.Presenter {
    private LoginContact.View mLoginContactView;

    public LoginPresenter(LoginContact.View mLoginContactView) {
        this.mLoginContactView = mLoginContactView;
        mLoginContactView.setPresenter(this);
    }

    @Override
    public void start() {
        String phone = BaseApplication.getInstance().getSharePre().getString("user_phone","");
        String pwd = BaseApplication.getInstance().getSharePre().getString("user_pwd","");
        mLoginContactView.setUserPhoneTVValue(phone);
        mLoginContactView.setUserPWDTVValue(pwd);
    }

    @Override
    public void login() {
        mLoginContactView.showLoading();
        AVUser.loginByMobilePhoneNumberInBackground(mLoginContactView.getUserNameTVValue(),
                mLoginContactView.getUserPWDTVValue(), new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        mLoginContactView.hideLoading();
                        if (e == null) {
                            BaseApplication.getInstance().getEditor().putString("user_name", avUser.getUsername());
                            BaseApplication.getInstance().getEditor().putString("user_phone", avUser.getMobilePhoneNumber());
                            BaseApplication.getInstance().getEditor().putString("user_pwd", mLoginContactView.getUserPWDTVValue());
                            BaseApplication.getInstance().getEditor().commit();
                            Log.i("login", "成功");
                            mLoginContactView.finish();
                        } else {
                            mLoginContactView.fialeNotice();
                            Log.e("login", "失败");
                        }

                    }
                });

    }
}
