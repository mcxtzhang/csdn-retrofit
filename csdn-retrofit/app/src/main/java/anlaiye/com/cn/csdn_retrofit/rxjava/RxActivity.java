package anlaiye.com.cn.csdn_retrofit.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import anlaiye.com.cn.csdn_retrofit.base.AppendHeaderParamInterceptor;
import anlaiye.com.cn.csdn_retrofit.base.AppendUrlParamInterceptor;
import anlaiye.com.cn.csdn_retrofit.base.NetworkConfig;
import anlaiye.com.cn.csdn_retrofit.R;
import anlaiye.com.cn.csdn_retrofit.normal.GankApi;
import anlaiye.com.cn.csdn_retrofit.normal.GetBean;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxActivity extends AppCompatActivity {

    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        mTvResult = (TextView) findViewById(R.id.tvResult);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //自动追加url参数
        builder.addInterceptor(new AppendUrlParamInterceptor());

        //自动追加header
        builder.addInterceptor(new AppendHeaderParamInterceptor());


        //OkHttp的Log信息拦截器
        if (NetworkConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }


        //Step1 拿到Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                //引入Gson解析库 ，就可以直接以实体的形式拿到返回值
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //将我们客制化的OkHttp实例传入
                .client(builder.build())
                .build();

        final GankApi gankApi = retrofit.create(GankApi.class);

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<GetBean> observable = gankApi.getDataByRx("Android", "10", "1");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<GetBean>() {
                            @Override
                            public void accept(GetBean getBean) throws Exception {
                                mTvResult.setText("Rxjava:\n" + getBean.getResults().get(1).getDesc());
                            }
                        });


            }
        });


        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<Object> observable = gankApi.postDataByRx("http://square.github.io/retrofit/",
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
                Completable completable = gankApi.postDataByRxNoReturn("http://square.github.io/retrofit/",
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

    }
}
