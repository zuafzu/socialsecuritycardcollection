package com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhkj.cyf.socialsecuritycardcollection.R;

import java.util.List;

public class ProvinceAdapter  extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
    public ProvinceAdapter(int layoutResId, @Nullable List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.textview, item.getLabel());
        helper.setTextColor(R.id.textview, item.isStatus() ? Color.parseColor("#005191") : Color.parseColor("#444444"));
    }
}