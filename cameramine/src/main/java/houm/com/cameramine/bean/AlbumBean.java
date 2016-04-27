package houm.com.cameramine.bean;

/**
 * Created by xuyaf on 2016/4/20.
 */
public class AlbumBean  {

    private String DirName;
    private int photoNums;

    private String firPicUrl;

    public AlbumBean(String dirName, int photoNums,String firPicUrl) {
        DirName = dirName;
        this.photoNums = photoNums;
        this.firPicUrl=firPicUrl;
    }

    public int getPhotoNums() {
        return photoNums;
    }

    public void setPhotoNums(int photoNums) {
        this.photoNums = photoNums;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public String getFirPicUrl() {
        return firPicUrl;
    }

    public void setFirPicUrl(String firPicUrl) {
        this.firPicUrl = firPicUrl;
    }

}
