package anlaiye.com.cn.csdn_retrofit.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import anlaiye.com.cn.csdn_retrofit.R;
import anlaiye.com.cn.csdn_retrofit.normal.GetBean;
import anlaiye.com.cn.net.RetrofitManager;
import anlaiye.com.cn.net.base.NetUtils;
import anlaiye.com.cn.net.base.NetworkConfig;
import anlaiye.com.cn.net.base.gson.BaseBean;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class RxActivity extends AppCompatActivity {

    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        NetUtils.init(this);

        mTvResult = (TextView) findViewById(R.id.tvResult);

        RetrofitManager.init(this);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //按照顺序执行拦截器
        //builder.addInterceptor(new PreHandleNoNetInterceptor());

        //自动追加url参数
        //builder.addInterceptor(new AppendUrlParamInterceptor());

        //自动追加header
        //builder.addInterceptor(new AppendHeaderParamInterceptor());

        //将URl参数->Body
        //builder.addInterceptor(new AppenBodyParamsInterceptor());

        //缓存设置
        //构建缓存位置
        File cacheFile = new File(getExternalCacheDir(), "csdn_retrofit");
        final Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //如果没有网络
                if (!NetUtils.isNetwork()) {
                    //取  缓存
                    Request newRequest = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    return chain.proceed(newRequest);
                } else {
                    // 有网 存

                    int maxTime = 60 * 60 * 24;
                    Response response = chain.proceed(request);
                    Response newResponse = response.newBuilder()

                            //套路代码 "http cache "
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                            .removeHeader("Progma")

                            .build();
                    return newResponse;
                }
            }
        };
        builder.cache(cache)
                .addInterceptor(cacheInterceptor);


        //okhttp 设置超时
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        //错误重连
        builder.retryOnConnectionFailure(true);




        //OkHttp的Log信息拦截器
        if (NetworkConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }


        //Step1 拿到Retrofit实例
/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")

                //引入Gson解析库 ，就可以直接以实体的形式拿到返回值
                //.addConverterFactory(GsonConverterFactory.create())

                //加入我们自定义的Gson解析库，就可以更友好的处理错误
                .addConverterFactory(CstGsonConverterFactory.create())


                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //将我们客制化的OkHttp实例传入
                .client(builder.build())
                .build();*/

        //final GankApi gankApi = retrofit.create(GankApi.class);

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<GetBean> observable =
                        RetrofitHelper.getGank()
                        .getDataByRx("Android", "10", "1");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GetBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(GetBean value) {
                                mTvResult.setText("Rxjava:\n" + value.getResults().get(1).getDesc());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("OkHttp", "!!!onError() called with: e = [" + e + "]");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        });


        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<Object> observable = RetrofitHelper.getGank()
                        .postDataByRx("http://square.github.io/retrofit/",
                        "测试数据",
                        "未来Android大佬",
                        "Android",
                        "true");

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object value) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


        Button btnCompletable = (Button) findViewById(R.id.btnUrl);
        btnCompletable.setText("Completable");
        btnCompletable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable completable = RetrofitHelper.getGank().postDataByRxNoReturn("http://square.github.io/retrofit/",

                        "测试数据",
                        "未来Android大佬",
                        "Android",
                        "true");

                //不关心返回值
                completable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(RxActivity.this, "成功了", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(RxActivity.this, "错:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        findViewById(R.id.btnRemoveWrapper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<BaseBean<List<BlogBean>>> android = RetrofitHelper.getGank()
                        .getDataByWrapper("Android", "10", "1");
                android.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseBean<List<BlogBean>>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseBean<List<BlogBean>> value) {
                                mTvResult.setText(value.getResults().get(2).getDesc());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        findViewById(R.id.btnRemoveWrapper2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<List<BlogBean>> dataNoWrapper = RetrofitHelper.getGank()
                        .getDataNoWrapper("Android", "10", "1");
                dataNoWrapper
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<BlogBean>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<BlogBean> value) {
                                mTvResult.setText("一共多少个数据" + value.size());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }
}
