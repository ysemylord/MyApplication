package houm.com.cameramine.bean;

import com.avos.avoscloud.AVObject;
import com.lorentzos.swipecards.CardMode;

import java.util.List;

/**
 * Created by xuyaf on 2016/4/17.
 */
public class MyCardBean extends CardMode{


    private AVObject joinerAVObj;


    public MyCardBean(String name, int year, List<String> images, AVObject joinerAVObj) {
        super(name, year, images);
        this.joinerAVObj = joinerAVObj;
    }

    public AVObject getJoinerAVObj() {
        return joinerAVObj;
    }

    public void setJoinerAVObj(AVObject joinerAVObj) {
        this.joinerAVObj = joinerAVObj;
    }
}
