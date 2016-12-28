package com.felink.netframework.core;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/12/28.
 */

public class HttpTask implements Runnable {

    private IHttpService httpService;

    private <T extends Serializable> HttpTask(T requestInfo, String url, IHttpListener iHttpListener){
        httpService = new JSONHttpService();
        httpService.setUrl(url);
        httpService.setHttpCallBack(iHttpListener);

        String requestContent = (new Gson()).toJson(requestInfo);
        try {
            httpService.setRequestData(requestContent.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        httpService.execute();
    }
}
