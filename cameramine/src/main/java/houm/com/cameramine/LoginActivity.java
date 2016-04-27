package houm.com.cameramine;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.presenter.LoginPresenter;
import houm.com.cameramine.view.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.container)
    FrameLayout mContainer;
    private FragmentManager mFragmentManager;
    private LoginFragment mLoginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction();
        mLoginFragment = (LoginFragment) mFragmentManager.findFragmentById(R.id.container);//View
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mLoginFragment)
            .commit();

        }

        new LoginPresenter(mLoginFragment);//Presenter

        getSupportActionBar().setTitle("登陆");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
