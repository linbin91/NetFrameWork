package com.felink.netframework.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */

public class ProductBean implements Serializable {

    public boolean hasNextPage;
    public int totalCount;
    public List<ProductItem> resources;


    public  static class ProductItem{
        public int id;
        public int status;
        public float yield;
        public String name;
        public String tips;
        public int type;
        public int recommend;
        public int leftCount;
    }
}
