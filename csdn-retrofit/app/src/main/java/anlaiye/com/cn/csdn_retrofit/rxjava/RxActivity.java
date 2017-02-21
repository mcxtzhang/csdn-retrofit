package anlaiye.com.cn.csdn_retrofit.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import anlaiye.com.cn.csdn_retrofit.R;
import anlaiye.com.cn.csdn_retrofit.normal.GankApi;
import anlaiye.com.cn.csdn_retrofit.normal.GetBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxActivity extends AppCompatActivity {

    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        mTvResult = (TextView) findViewById(R.id.tvResult);

        //Step1 拿到Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                //引入Gson解析库 ，就可以直接以实体的形式拿到返回值
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        final GankApi gankApi = retrofit.create(GankApi.class);

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<GetBean> observable = gankApi.getDataByRx("Android", "10", "1");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Function<GetBean, ObservableSource<GetBean>>() {
                            @Override
                            public ObservableSource<GetBean> apply(GetBean getBean) throws Exception {
                                //另外一个接口
                                return null;
                            }
                        })
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

    }
}
