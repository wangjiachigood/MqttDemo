package com.pb.joindata.mqttdemo.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copyright: 2016 Joindata. All rights reserved.
 * author：===>>     panic     <===
 * Create Time:   ===>>  2017/6/14 18:24  <===
 * email：panic4java@gmail.com
 * describe:主页面list bean
 * ********************************************************
 */
public class OrderListBean implements Serializable {
    //"下单时间")
    public Long createTime;
    //"订单金额")
    public BigDecimal totalprice;
    //"就餐号")
    public String eatNumber;
    //"桌名")
    public String tableName;
    //"预点类型 1.现点堂吃 2.现点打包 3.预点堂吃 4.预点打包")
    public String eatStyle;
}
