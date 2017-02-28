package anlaiye.com.cn.csdn_retrofit.edu;

import anlaiye.com.cn.csdn_retrofit.normal.GetBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/28.
 */
public interface EduApi {
    /**
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET("api/data/{category}/{size}/{page}")
    Call<GetBean> getData(@Path("category") String a,
                          @Path("size") String b,
                          @Path("page") String c);
}
