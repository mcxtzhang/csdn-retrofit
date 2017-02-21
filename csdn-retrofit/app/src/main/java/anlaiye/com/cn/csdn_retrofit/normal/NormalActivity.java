package anlaiye.com.cn.csdn_retrofit.normal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import anlaiye.com.cn.csdn_retrofit.R;
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
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step1 拿到Retrofit实例
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://gank.io/")
                        //引入Gson解析库 ，就可以直接以实体的形式拿到返回值
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Step2 拿到我们的Api 实例
                GankApi gankApi = retrofit.create(GankApi.class);

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


    }
}
