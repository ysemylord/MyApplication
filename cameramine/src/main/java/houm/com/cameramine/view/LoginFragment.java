package houm.com.cameramine.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.R;
import houm.com.cameramine.contact.LoginContact;
import houm.com.cameramine.presenter.LoginPresenter;

/**
 * Created by Administrator on 2016/4/18.
 */
public class LoginFragment extends Fragment implements LoginContact.View {

    LoginContact.Presenter mPresenter;
    ProgressDialog mProgressDialog;
    @Bind(R.id.user_name_et)
    EditText mUserNameEt;
    @Bind(R.id.user_pwd_et)
    EditText mUserPwdEt;
    @Bind(R.id.login_btn)
    Button mLoginBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_layout, container, false);
        mProgressDialog = new ProgressDialog(getActivity());
        ButterKnife.bind(this, root);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
            }
        });
        return root;
    }

    /**
     * 在Fragmetn的onResume中调用Presenter.start方法，做初始化工作
     */
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }

    @Override
    public String getUserNameTVValue() {
        return mUserNameEt.getText().toString().trim();
    }

    @Override
    public String getUserPWDTVValue() {
        return mUserPwdEt.getText().toString().trim();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
