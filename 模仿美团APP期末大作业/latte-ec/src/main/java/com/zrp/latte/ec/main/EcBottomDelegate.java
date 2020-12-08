package com.zrp.latte.ec.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.zrp.latte.delegates.bottom.BaseBottomDelegate;
import com.zrp.latte.delegates.bottom.BottomItemDelegate;
import com.zrp.latte.delegates.bottom.BottomTabBean;
import com.zrp.latte.delegates.bottom.ItemBuilder;
import com.zrp.latte.ec.main.cart.ShopCartDelegate;
import com.zrp.latte.ec.main.discover.DiscoverDelegate;
import com.zrp.latte.ec.main.index.IndexDelegate;
import com.zrp.latte.ec.main.personal.PersonalDelegate;
import com.zrp.latte.ec.main.sort.SortDelegate;


import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate{

    private BottomItemDelegate indexDelegate = new IndexDelegate();

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","首页"), indexDelegate);
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"), new PersonalDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

        if (requestCode == 11 && resultCode == RESULT_OK) {
            indexDelegate.onFragmentResult(requestCode, resultCode, data);
        }
    }


}
