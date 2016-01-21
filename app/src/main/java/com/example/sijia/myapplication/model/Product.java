package com.example.sijia.myapplication.model;

import java.util.List;

/**
 * Created by xu on 2016/1/20.
 */
public class Product {


    /**
     * code : 200
     * hasmore : true
     * page_total : 15
     * datas : {"goods_list":[{"goods_id":"100675","gc_id":"1105","goods_name":"22222222222222222222","goods_price":"0.01","goods_marketprice":"0.01","goods_image":"30_05062036094820091.jpg","goods_salenum":"20","evaluation_good_star":"5","evaluation_count":"0","is_virtual":"0","is_presell":"0","is_fcode":"0","have_gift":"0","group_flag":false,"xianshi_flag":false,"goods_image_url":"http://chuanchi.test.kh888.cn/data/upload/shop/store/goods/30/30_05062036094820091_360.jpg"},{"goods_id":"100667","gc_id":"1105","goods_name":"视音频1","goods_price":"11.00","goods_marketprice":"22.00","goods_image":"1_05059063122336231.png","goods_salenum":"34","evaluation_good_star":"5","evaluation_count":"0","is_virtual":"0","is_presell":"0","is_fcode":"0","have_gift":"0","group_flag":false,"xianshi_flag":false,"goods_image_url":"http://chuanchi.test.kh888.cn/data/upload/shop/store/goods/1/1_05059063122336231_360.png"},{"goods_id":"100666","gc_id":"1105","goods_name":"视音频","goods_price":"33.00","goods_marketprice":"66.00","goods_image":"1_05059060264554716.png","goods_salenum":"3","evaluation_good_star":"5","evaluation_count":"0","is_virtual":"0","is_presell":"0","is_fcode":"0","have_gift":"0","group_flag":false,"xianshi_flag":false,"goods_image_url":"http://chuanchi.test.kh888.cn/data/upload/shop/store/goods/1/1_05059060264554716_360.png"},{"goods_id":"100606","gc_id":"1105","goods_name":"测试001 韩国 小号 专用面板","goods_price":"0.01","goods_marketprice":"0.50","goods_image":"1_05013452639761175.jpg","goods_salenum":"9","evaluation_good_star":"4","evaluation_count":"2","is_virtual":"0","is_presell":"0","is_fcode":"0","have_gift":"0","group_flag":false,"xianshi_flag":false,"goods_image_url":"http://chuanchi.test.kh888.cn/data/upload/shop/store/goods/1/1_05013452639761175_360.jpg"}]}
     */

    private int code;
    private boolean hasmore;
    private int page_total;
    private DatasEntity datas;

    public void setCode(int code) {
        this.code = code;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public int getCode() {
        return code;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public int getPage_total() {
        return page_total;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * goods_id : 100675
         * gc_id : 1105
         * goods_name : 22222222222222222222
         * goods_price : 0.01
         * goods_marketprice : 0.01
         * goods_image : 30_05062036094820091.jpg
         * goods_salenum : 20
         * evaluation_good_star : 5
         * evaluation_count : 0
         * is_virtual : 0
         * is_presell : 0
         * is_fcode : 0
         * have_gift : 0
         * group_flag : false
         * xianshi_flag : false
         * goods_image_url : http://chuanchi.test.kh888.cn/data/upload/shop/store/goods/30/30_05062036094820091_360.jpg
         */

        private List<GoodsListEntity> goods_list;

        public void setGoods_list(List<GoodsListEntity> goods_list) {
            this.goods_list = goods_list;
        }

        public List<GoodsListEntity> getGoods_list() {
            return goods_list;
        }

        public static class GoodsListEntity {
            private String goods_id;
            private String gc_id;
            private String goods_name;
            private String goods_price;
            private String goods_marketprice;
            private String goods_image;
            private String goods_salenum;
            private String evaluation_good_star;
            private String evaluation_count;
            private String is_virtual;
            private String is_presell;
            private String is_fcode;
            private String have_gift;
            private boolean group_flag;
            private boolean xianshi_flag;
            private String goods_image_url;

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public void setGoods_salenum(String goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public void setEvaluation_good_star(String evaluation_good_star) {
                this.evaluation_good_star = evaluation_good_star;
            }

            public void setEvaluation_count(String evaluation_count) {
                this.evaluation_count = evaluation_count;
            }

            public void setIs_virtual(String is_virtual) {
                this.is_virtual = is_virtual;
            }

            public void setIs_presell(String is_presell) {
                this.is_presell = is_presell;
            }

            public void setIs_fcode(String is_fcode) {
                this.is_fcode = is_fcode;
            }

            public void setHave_gift(String have_gift) {
                this.have_gift = have_gift;
            }

            public void setGroup_flag(boolean group_flag) {
                this.group_flag = group_flag;
            }

            public void setXianshi_flag(boolean xianshi_flag) {
                this.xianshi_flag = xianshi_flag;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getGc_id() {
                return gc_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public String getGoods_salenum() {
                return goods_salenum;
            }

            public String getEvaluation_good_star() {
                return evaluation_good_star;
            }

            public String getEvaluation_count() {
                return evaluation_count;
            }

            public String getIs_virtual() {
                return is_virtual;
            }

            public String getIs_presell() {
                return is_presell;
            }

            public String getIs_fcode() {
                return is_fcode;
            }

            public String getHave_gift() {
                return have_gift;
            }

            public boolean isGroup_flag() {
                return group_flag;
            }

            public boolean isXianshi_flag() {
                return xianshi_flag;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }
        }
    }
}
