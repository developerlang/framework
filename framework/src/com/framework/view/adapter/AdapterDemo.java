package com.framework.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 
 * @author zhangbc
 *
 */
public class AdapterDemo<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    public AdapterDemo(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, T item) {
        helper.setText(android.R.id.text1, item.toString());
    }

    @Override
    protected BaseAdapterHelper getAdapterHelper(int position,
            View convertView, ViewGroup parent) {

        return BaseAdapterHelper.get(context, convertView, parent, layoutResId);
    }

}
