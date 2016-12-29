package com.felink.netframework.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/29.
 */

public class PageBean implements Serializable {

    public int pageSize;
    public int pageNo;

    public PageBean(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;

    }
}
