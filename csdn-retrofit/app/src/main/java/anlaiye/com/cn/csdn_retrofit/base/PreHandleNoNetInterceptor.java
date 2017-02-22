package anlaiye.com.cn.csdn_retrofit.base;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 介绍：无网络 异常 处理拦截器
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/22.
 */
public class PreHandleNoNetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (NetUtils.isNetwork()) {
            return chain.proceed(chain.request());
        } else {
            throw new ResultException(NetworkConfig.ERROR_CODE_NO_NET, "当前网络不通，请确认网络后重试");
        }
    }
}
