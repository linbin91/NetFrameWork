package com.felink.netframework;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.felink.netframework.bean.PageBean;
import com.felink.netframework.bean.ProductBean;
import com.felink.netframework.core.HttpTask;
import com.felink.netframework.core.IJSonListener;
import com.felink.netframework.core.JsonDealListener;
import com.felink.netframework.core.ThreadPoolManager;

import java.util.concurrent.FutureTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://139.196.173.191:42000/blb-api/product";

                PageBean bean = new PageBean(10,1);

                HttpTask httpTask = new HttpTask(bean,url, new JsonDealListener<ProductBean>(ProductBean.class,new IJSonListener<ProductBean>() {
                    @Override
                    public void onSuccess(ProductBean productBean) {
                        if (productBean != null){
                            Log.e("linbin",productBean.resources.get(0).name);
                        }
                    }
                }));

                ThreadPoolManager.getInstance().execute(new FutureTask<Object>(httpTask,null));
            }
        });

    }
}
