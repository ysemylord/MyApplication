package com.example.sijia.myapplication;

import junit.framework.Assert;

import org.junit.Test;

import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        DecimalFormat fnum = new DecimalFormat("0.00");
        int limit_num=10;
        float coupon_price=10f;
        Assert.assertEquals(50.0f,
                getSeondSpecialSingleCouponPrice( 5,10,
                        100f,50f,
                        10f));
        Assert.assertEquals(21f,
                getSeondSpecialSingleCouponPrice( 5,10,
                        5f,1f,
                        10f));
        Assert.assertEquals(41.0f,
                getSeondSpecialSingleCouponPrice( 5,10,
                        100f,1f,
                        10f));

        Assert.assertEquals(50.0f,
                getSeondSpecialSingleCouponPrice( 10,5,
                        100f,50f,
                        10f));
        Assert.assertEquals(25.0f,
                getSeondSpecialSingleCouponPrice( 10,5,
                        5f,1f,
                        10f));
        Assert.assertEquals(50.0f,
                getSeondSpecialSingleCouponPrice( 10,5,
                        100f,1f,
                        10f));

        Assert.assertEquals(10.0f,
                getSeondSpecialSingleCouponPrice( 1,5,
                        100f,1f,
                        10f));

    }

    /**
     * 第二件特价同时使用单品优惠券时享受的优惠金额
     * @param buy_num
     * @param limit_num
     * @param origin_price
     * @param second_price
     * @param coupon_price
     * @return
     */
    private float getSeondSpecialSingleCouponPrice(int buy_num, int limit_num,
                                                   float origin_price, float second_price,
                                                   float coupon_price){
        float coupon_money=0f;
        int real_coupon_num=Math.min(buy_num,limit_num);//真正的优惠数量
        float real_coupon_price=Math.min(origin_price,coupon_price);//真正的优惠价格
        float second_real_coup_price=(Math.min(second_price,coupon_price));//第二件特价中享受特价的商品的优惠价格
        boolean second_price_coupon=(buy_num<=limit_num);//最后一件特价商品是否享受优惠

        if(buy_num==1){
            coupon_money= real_coupon_num*real_coupon_price;
            return coupon_money;
        }

        if(second_price_coupon){
            coupon_money=real_coupon_price*(real_coupon_num-1)+(Math.min(second_price,coupon_price))*1;
        }else{
            coupon_money=real_coupon_num*real_coupon_price;
        }
        return coupon_money;
    }

    private void testMD5() {
        String clinet = "ios+324235435435";
        String str = MD5(clinet + "+ggCJall=3");
        System.out.println(str);
    }

    public final String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}