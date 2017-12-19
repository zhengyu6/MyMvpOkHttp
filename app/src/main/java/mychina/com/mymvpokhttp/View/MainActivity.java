package mychina.com.mymvpokhttp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mychina.com.mymvpokhttp.Presenter.IHttpPresenterCompos;
import mychina.com.mymvpokhttp.R;
import mychina.com.mymvpokhttp.View.IHttpView;

public class MainActivity extends AppCompatActivity implements IHttpView,View.OnClickListener {

    private TextView tv;
    private Button btn_get;
    private Button btn_post;
    private IHttpPresenterCompos presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new IHttpPresenterCompos(this);
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);

        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                presenter.get();
                break;
            case R.id.btn_post:
                presenter.post();
                break;
        }

    }

    @Override
    public void onGetText(String result) {
        tv.setText(result);

    }

    @Override
    public void onPostText(String result) {
        tv.setText(result);

    }
}
