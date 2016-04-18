package houm.com.cameramine.contact;

import houm.com.cameramine.presenter.BasePresenter;
import houm.com.cameramine.presenter.LoginPresenter;
import houm.com.cameramine.view.BaseView;

/**
 * Created by Administrator on 2016/4/18.
 */
public class LoginContact {
    public interface View extends BaseView<LoginPresenter> {
        void showLoading();

        void hideLoading();

        String getUserNameTVValue();

        String getUserPWDTVValue();

    }

    public interface Presenter extends BasePresenter {
        void login();

    }


}
