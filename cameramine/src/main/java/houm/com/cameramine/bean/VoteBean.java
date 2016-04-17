package houm.com.cameramine.bean;

/**
 * Created by xuyaf on 2016/4/17.
 */
public class VoteBean {
    private String ImageUrl;
    private int voteNum;

    public VoteBean(String imageUrl, int voteNum) {
        ImageUrl = imageUrl;
        this.voteNum = voteNum;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
