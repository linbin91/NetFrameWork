package com.felink.netframework.core;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/12/28.
 */

public class JsonDealListener<M> implements IHttpListener {

    private Class<M> responseClass;
    private IJSonListener<M> jsonListener;
    private Handler handler = new Handler();

    public JsonDealListener(Class<M> responseClass, IJSonListener<M> jsonListener){
        this.responseClass = responseClass;
        this.jsonListener = jsonListener;
    }
    @Override
    public void onSuccess(InputStream inputStream) {
        String content = getContent(inputStream);
        Gson gson = new Gson();
        final M response = gson.fromJson(content,responseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (jsonListener != null){
                    jsonListener.onSuccess(response);
                }
            }
        });
    }

    private String getContent(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
