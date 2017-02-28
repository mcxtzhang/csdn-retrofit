package anlaiye.com.cn.net.base;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 介绍：真的偷天换日，将所有的Get-》Post，将Get后面的Query Pamras->Body(基本用不到)
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/22.
 */
public class AppenBodyParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //拿到所有Query 的 key
        Set<String> queryKeyName = request.url().queryParameterNames();
        StringBuilder bodyString = new StringBuilder();
        //将query->Body
        for (String s : queryKeyName) {
            bodyString
                    .append(s)
                    .append("=")
                    //查询url后面的key value
                    .append(request.url().queryParameterValues(s))
                    .append(",");
        }
        //构建新Body
        RequestBody newBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded")
                , bodyString.toString().substring(0, bodyString.toString().length() - 1));

        Request newRequest = request.newBuilder()
                .post(newBody)
                .build();


        return chain.proceed(newRequest);
    }
}
