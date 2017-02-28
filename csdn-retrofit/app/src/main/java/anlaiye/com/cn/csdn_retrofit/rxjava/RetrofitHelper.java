package anlaiye.com.cn.csdn_retrofit.rxjava;

import anlaiye.com.cn.csdn_retrofit.edu.EduApi;
import anlaiye.com.cn.csdn_retrofit.normal.GankApi;
import anlaiye.com.cn.net.RetrofitManager;
import retrofit2.Retrofit;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/28.
 */
public class RetrofitHelper {
    public static final String GANK_URL = "http://gank.io/";
    //新增了一个EDU的业务线
    public static final String EDU_URL = "http://edu.csdn.net/";

    //一个URL- 一个Retrofit实例
    private static final GankApi mGankApi = RetrofitManager
            .getInstance()
            .newRetrofit(GANK_URL)
            .create(GankApi.class);

    private static final EduApi mEduApi = RetrofitManager.getInstance()
            .newRetrofit(EDU_URL)
            .create(EduApi.class);

    public static GankApi getGank() {
        return mGankApi;
    }

    public static <T> T create(Class class1) {
        return (T) new Retrofit.
                Builder().
                baseUrl("").
                build().
                create(class1);
    }

    public static EduApi getEdu() {
        return mEduApi;
    }
}
