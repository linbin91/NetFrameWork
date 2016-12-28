package com.felink.netframework.core;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface IHttpService {

    public void setUrl(String url);

    public void execute();

    public void setHttpCallBack(IHttpListener httpListener);

    public void setRequestData(byte[] requestData);

}
