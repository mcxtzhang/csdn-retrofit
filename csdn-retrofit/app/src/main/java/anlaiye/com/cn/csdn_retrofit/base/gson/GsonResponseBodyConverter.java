package anlaiye.com.cn.csdn_retrofit.base.gson;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.Type;

import anlaiye.com.cn.csdn_retrofit.base.ResultException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //▲ value 服务器回传给我们的body
        String response = value.string();

        //构建泛型的type  BaseBean<type>
        Type baseBeanType = $Gson$Types.newParameterizedTypeWithOwner(null, BaseBean.class, type);


        BaseBean baseBean = gson.fromJson(response, baseBeanType);
        if (baseBean.isError()) {
            throw new ResultException(baseBean.geteCode(), baseBean.geteMsg());
        } else {
            //成功返回 继续用原来的 Type类 解析

            return (T) baseBean.getResults();
        }

    }
}