package com.felink.netframework.core;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/12/28.
 */

public class JSONHttpService implements IHttpService {
    private String url;
    private IHttpListener httpListener;
    private byte[] requestData;
    private HttpClient httpClient = new DefaultHttpClient();
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void execute() {
        HttpPost  httpPost = new HttpPost(url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestData);
        httpPost.setEntity(byteArrayEntity);
        try {
           HttpResponse response =  httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200){
                InputStream is = response.getEntity().getContent();
                if (httpListener != null){
                    httpListener.onSuccess(is);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setHttpCallBack(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }
}
