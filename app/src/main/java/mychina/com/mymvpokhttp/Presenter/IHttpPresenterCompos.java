package mychina.com.mymvpokhttp.Presenter;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import mychina.com.mymvpokhttp.View.IHttpView;

/**
 * Created by 张晓辉 on 2017/12/18.
 */

public class IHttpPresenterCompos implements IHttpPresenter {
    public IHttpView iHttpView;
    public IHttpPresenterCompos(IHttpView iHttpView) {
        this.iHttpView = iHttpView;
    }


    @Override
    public void get() {
        String url = "http://v.juhe.cn/toutiao/index";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("type", "junshi")
                .addParams("key", "5b257197a4cb756552cd26e775256c49")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {

                    }

                    @Override
                    public void onResponse(String response)
                    {
                        String sget = response.toString();
                        Message msg1 = handler.obtainMessage();
                        msg1.what=1;
                        msg1.obj=sget;
                        handler.sendMessage(msg1);
                    }
                });
    }

    @Override
    public void post() {
        OkHttpUtils
                .post()
                .url("http://v.juhe.cn/toutiao/index")
                .addParams("type", "yule")
                .addParams("key", "5b257197a4cb756552cd26e775256c49")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        String spost = response.toString();
                        Message msg = handler.obtainMessage();
                        msg.what=2;
                        msg.obj=spost;
                        handler.sendMessage(msg);


                    }
                });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String s = (String) msg.obj;
                iHttpView.onGetText("根据参数" + "的请求网络数据成功" + s);
            }
            if (msg.what == 2) {
                String s2 = (String) msg.obj;
                iHttpView.onGetText("根据参数" + "的请求网络数据成功" + s2);
            }
            super.handleMessage(msg);
        }
    };
}
