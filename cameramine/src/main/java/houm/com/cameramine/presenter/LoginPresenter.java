package houm.com.cameramine.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

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
                            Log.i("login", "成功");
                        } else {
                            Log.e("login", "失败");
                        }

                    }
                });

    }
}
