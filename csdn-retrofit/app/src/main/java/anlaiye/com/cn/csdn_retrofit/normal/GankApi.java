package anlaiye.com.cn.csdn_retrofit.normal;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/21.
 */
public interface GankApi {
    /**
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET("api/data/{category}/{size}/{page}")
    Call<GetBean> getData(@Path("category") String a,
                          @Path("size") String b,
                          @Path("page") String c);


    /**
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET
    Call<GetBean> getDataWithUrl(@Url String url);


    /**
     * 提交表单格式如下:
     * 字段	描述	备注
     * url	想要提交的网页地址
     * desc	对干货内容的描述	单独的文字描述
     * who	提交者 ID	干货提交者的网络 ID
     * type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     * debug	当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
     */
    @FormUrlEncoded
    @POST("api/add2gank")
    Call<ResponseBody> postData(@Field("url") String url,
                                @Field("desc") String desc,
                                @Field("who") String who,
                                @Field("type") String type,
                                @Field("debug") String debug);

    /**
     * Rxjava的形式返回
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET("api/data/{category}/{size}/{page}")
    Observable<GetBean> getDataByRx(@Path("category") String a,
                                    @Path("size") String b,
                                    @Path("page") String c);


    @FormUrlEncoded
    @POST("api/add2gank")
    Observable<Object> postDataByRx(@Field("url") String url,
                                @Field("desc") String desc,
                                @Field("who") String who,
                                @Field("type") String type,
                                @Field("debug") String debug);

    @FormUrlEncoded
    @POST("api/add2gank")
    Completable postDataByRxNoReturn(@Field("url") String url,
                                     @Field("desc") String desc,
                                     @Field("who") String who,
                                     @Field("type") String type,
                                     @Field("debug") String debug);
}
