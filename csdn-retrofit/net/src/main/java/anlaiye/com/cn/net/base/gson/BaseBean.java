package anlaiye.com.cn.net.base.gson;

/**
 * 介绍：与服务器约定好的 统一返回格式
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/22.
 */
public class BaseBean<T> {
    private T results;
    private boolean error;
    private String eCode = "-10003";
    private String eMsg = "服务器统一错误";

    public String geteMsg() {
        return eMsg;
    }

    public BaseBean seteMsg(String eMsg) {
        this.eMsg = eMsg;
        return this;
    }

    public String geteCode() {
        return eCode;
    }

    public BaseBean seteCode(String eCode) {
        this.eCode = eCode;
        return this;
    }

    public T getResults() {
        return results;
    }

    public BaseBean setResults(T results) {
        this.results = results;
        return this;
    }

    public boolean isError() {
        return error;
    }

    public BaseBean setError(boolean error) {
        this.error = error;
        return this;
    }
}
