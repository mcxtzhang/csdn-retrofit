package anlaiye.com.cn.csdn_retrofit.normal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import anlaiye.com.cn.csdn_retrofit.R;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NormalActivity extends AppCompatActivity {
    TextView mTvResult;
    private String TAG = "TAG";

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
                .build();

        //Step2 拿到我们的Api 实例
        final GankApi gankApi = retrofit.create(GankApi.class);

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Step3 通过Api 拿到 call
                Call<GetBean> android = gankApi.getData("Android", "10", "1");

                //Step 4 请求网络
                android.enqueue(new Callback<GetBean>() {
                    @Override
                    public void onResponse(Call<GetBean> call, Response<GetBean> response) {
                        Toast.makeText(NormalActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                        GetBean getBean = response.body();
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + getBean + "]");

                        mTvResult.setText(getBean.getResults().get(0).getDesc());
                    }

                    @Override
                    public void onFailure(Call<GetBean> call, Throwable t) {
                        Toast.makeText(NormalActivity.this, "请求失败:" + t, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 提交表单格式如下:
                 * 字段	描述	备注
                 * url	想要提交的网页地址
                 * desc	对干货内容的描述	单独的文字描述
                 * who	提交者 ID	干货提交者的网络 ID
                 * type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
                 * debug	当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
                 */
                Call<ResponseBody> postData = gankApi.postData("http://square.github.io/retrofit/",
                        "测试数据",
                        "未来Android大佬",
                        "Android",
                        "true");
                //Step 4 请求网络
                postData.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(NormalActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(NormalActivity.this, "请求失败:" + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btnUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step3 通过Api 拿到 call
                Call<GetBean> android = gankApi.getDataWithUrl("http://gank.io/api/data/Android/10/1");

                android.enqueue(new Callback<GetBean>() {
                    @Override
                    public void onResponse(Call<GetBean> call, Response<GetBean> response) {
                        mTvResult.setText(response.body().getResults().get(0).getDesc());
                    }

                    @Override
                    public void onFailure(Call<GetBean> call, Throwable t) {

                    }
                });
            }
        });

    }
}
