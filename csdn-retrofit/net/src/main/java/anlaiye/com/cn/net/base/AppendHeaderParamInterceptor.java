package anlaiye.com.cn.net.base;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 介绍：偷天换日 统一追加Header 参数
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/22.
 */
public class AppendHeaderParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //核心也是通过newBuilder 拿到Builder
        Headers.Builder builder = request.
                headers().
                newBuilder();

        //统一追加header参数
        Headers newHeader = builder.add("header1", "iam header1")
                .add("token", "iam tokem")
                .add("uid", "987654321")
                .build();

        Request newRequest = request.newBuilder()
                .headers(newHeader)
                .build();


        return chain.proceed(newRequest);
    }
}
