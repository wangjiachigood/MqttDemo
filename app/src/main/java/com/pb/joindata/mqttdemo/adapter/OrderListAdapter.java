package com.pb.joindata.mqttdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pb.joindata.mqttdemo.R;
import com.pb.joindata.mqttdemo.bean.OrderListBean;
import com.pb.joindata.mqttdemo.utils.TimeUtil;

import java.util.List;


/**
 * Copyright: 2016 Joindata. All rights reserved.
 * author：===>>     panic     <===
 * Create Time:   ===>>  2017/6/6 16:55  <===
 * email：panic4java@gmail.com
 * describe:
 * ********************************************************
 */
public class OrderListAdapter extends BaseAdapter {

    private List<OrderListBean> list;

    Context mContext;


    public OrderListAdapter(List<OrderListBean> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder hodler;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_home_list, null);
            hodler = new ViewHolder();
            hodler.tvHomeDishNumber = (TextView) view.findViewById(R.id.tv_home_dish_number);
            hodler.tvHomeListDishMoney= (TextView) view.findViewById(R.id.tv_home_list_dish_money);
            hodler.tvHomeListDishTime= (TextView) view.findViewById(R.id.tv_home_list_dish_time);
            hodler.tvHomeListTableName= (TextView) view.findViewById(R.id.tv_home_list_table_name);
            hodler.ivHomeListIcon= (ImageView) view.findViewById(R.id.iv_home_list_icon);
            view.setTag(hodler);
        } else {
            hodler = (ViewHolder) view.getTag();
        }
        //点餐类型
        if (!TextUtils.isEmpty(list.get(i).eatStyle)) {
            int eatStyle = Integer.parseInt(list.get(i).eatStyle);
            if (eatStyle < 1 || eatStyle > 4) {
                hodler.ivHomeListIcon.setImageResource(hodler.icons[0]);
            }
            hodler.ivHomeListIcon.setImageResource(hodler.icons[eatStyle]);
        } else {
            hodler.ivHomeListIcon.setImageResource(hodler.icons[0]);
        }
        hodler.tvHomeDishNumber.setText(list.get(i).eatNumber);
        //桌号
        hodler.tvHomeListTableName.setText(list.get(i).tableName);
        //金额
        hodler.tvHomeListDishMoney.setText("￥" + (list.get(i).totalprice == null ? "--" : list.get(i).totalprice));
        //下单时间
        hodler.tvHomeListDishTime.setText(TimeUtil.formatDateWithLastDay("HH:mm:ss", list.get(i).createTime));

        return view;
    }

    static class ViewHolder {
        int[] icons = new int[]{R.drawable.icon_wx_order, R.drawable.icon_home_list_now_eatting,
                R.drawable.icon_home_list_now_bale,
                R.drawable.icon_home_list_expetcted_eatting,
                R.drawable.icon_home_list_expected_bale};
        private TextView tvHomeDishNumber, tvHomeListTableName, tvHomeListDishMoney, tvHomeListDishTime;
        private ImageView ivHomeListIcon;
    }

}
