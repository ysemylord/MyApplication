package com.lorentzos.swipecards;

import java.util.List;

/**
 * Created by Shall on 2015-06-23.
 */
public class CardMode {
    private String name;
    private int voteNum;
    private List<String> images;




    public CardMode(String name, int voteNum, List<String> images) {
        this.name = name;
        this.voteNum = voteNum;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }
}
