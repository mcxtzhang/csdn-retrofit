package anlaiye.com.cn.csdn_retrofit.rxjava;

import anlaiye.com.cn.csdn_retrofit.normal.GankApi;
import anlaiye.com.cn.net.RetrofitManager;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/28.
 */
public class GankRetrofit {
    public static final String URL = "http://gank.io/";

    private static final GankApi mGankApi = RetrofitManager
            .getInstance()
            .newRetrofit(URL)
            .create(GankApi.class);

    public static GankApi get() {
        return mGankApi;
    }
}
