package com.zrp.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.latte.latte_ec.R;
import com.example.latte.latte_ec.R2;
import com.joanzapata.iconify.widget.IconTextView;
import com.zrp.latte.app.Latte;
import com.zrp.latte.delegates.bottom.BottomItemDelegate;
import com.zrp.latte.ec.main.cart.like.ShopCartLikeAdapter;
import com.zrp.latte.ec.main.cart.like.ShopCartLikeConverter;
import com.zrp.latte.ec.main.cart.order.SettleDelegate;
import com.zrp.latte.ec.main.index.IndexAndSpecBean;
import com.zrp.latte.ec.main.index.IndexDelegate;
import com.zrp.latte.net.RestClient;
import com.zrp.latte.net.callback.ISuccess;
import com.zrp.latte.ui.recycler.MultipleItemEntity;
import com.zrp.latte.ui.recyclerview.ShopCartItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener {


    private ShopCartAdapter mAdapter;
    private int mCurrentCount = 0;
    private int mTotalCount = 0;

    @BindView(R2.id.tv_top_shop_cart_clear)
    AppCompatTextView tvTopShopCartClear;
    @BindView(R2.id.tv_top_shop_cart_remove_selected)
    AppCompatTextView tvTopShopCartRemoveSelected;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice;
    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView tvShopCartPay;
    @BindView(R2.id.rv_you_like)
    RecyclerView mYouLikeRecyclerView;

    //购物车商品全选
    @OnClick(R2.id.icon_shop_cart_select_all)
    void iconShopCartSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTag(1);
            mIconSelectAll.setTextColor(
                    ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
            //全选状态
            mAdapter.setIsSelectedAll(true);
            //批量更新 起始位置:0
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTag(0);
            mIconSelectAll.setTextColor(Color.GRAY);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    //删除已选择的商品
    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void removeSelected() {
        final List<MultipleItemEntity> data = mAdapter.getData();

        List<MultipleItemEntity> deletedEntity = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deletedEntity.add(entity);
            }
        }
        for (MultipleItemEntity entity : deletedEntity) {
            //int removePisotion;

            final int removePisotion = entity.getField(ShopCartItemFields.POSITION);
            entity.getField(ShopCartItemFields.TITLE);
            if (removePisotion <= mAdapter.getItemCount()) {
                mAdapter.getData().remove(entity);
                mAdapter.remove(removePisotion);
                mCurrentCount = mAdapter.getItemCount();
                //mAdapter.notifyItemRangeChanged(removePisotion, mAdapter.getItemCount());
                mAdapter.notifyDataSetChanged();
            }
        }
        checkItemCount();
    }

    //清空购物车
    @OnClick(R2.id.tv_top_shop_cart_clear)
    void shopCartClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        mTvTotalPrice.setText("0");
        checkItemCount();
    }

    /**
     * 结算
     */
    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickSettle() {
        getParentDelegate().getSupportDelegate().start(new SettleDelegate());
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcart;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View view) {
        super.onBindView(savedInstanceState, view);
        //初始化全选按钮的Tag  默认全部选中状态
        mIconSelectAll.setTag(1);
	    mIconSelectAll.setTextColor(
			    ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //zip 操作符合并请求数据
        //购物车数据
        Observable<String> shopObservable = RestClient.builder()
                .url("api/shopcart")
                .build()
                .get()
                .subscribeOn(Schedulers.io());
        //猜你喜欢 数据
        Observable<String> likeObservable = RestClient.builder()
                .url("api/youlike")
                .build()
                .get()
                .subscribeOn(Schedulers.io());
        Disposable dis = Observable.zip(shopObservable, likeObservable, new BiFunction<String, String, IndexAndSpecBean>() {
                    @Override
                    public IndexAndSpecBean apply(String s, String s2) {
                        IndexAndSpecBean indexAndSpecBean = new IndexAndSpecBean();
                        indexAndSpecBean.data1 = s;
                        indexAndSpecBean.data2 = s2;
                        return indexAndSpecBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IndexAndSpecBean>() {
                    @Override
                    public void accept(IndexAndSpecBean indexAndSpecBean) {
                        //shopcart数据
                        final LinkedList<MultipleItemEntity> data =
                                new ShopCartDataConverter().setJsonData(indexAndSpecBean.data1).convert();
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mAdapter = new ShopCartAdapter(data);
                        mAdapter.setCartItemListener(ShopCartDelegate.this);
                        final ItemTouchHelper.Callback callback = new ShopCartItemTouchHelperCallback(mAdapter);
                        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                        itemTouchHelper.attachToRecyclerView(mRecyclerView);
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setLayoutManager(manager);
                        checkItemCount();
                        final double totalPrice = mAdapter.getTotalPrice();
                        mTvTotalPrice.setText(String.valueOf(totalPrice));
                        //猜你喜欢数据
                        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                        mYouLikeRecyclerView.setLayoutManager(gridLayoutManager);
                        final List<MultipleItemEntity> data2 = new ShopCartLikeConverter().setJsonData(indexAndSpecBean.data2).convert();
                        final ShopCartLikeAdapter likeAdapter = new ShopCartLikeAdapter(data2);
                        mYouLikeRecyclerView.setAdapter(likeAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    //购物车数据回调
    @Override
    public void onSuccess(String response) {
        final LinkedList<MultipleItemEntity> data =
                new ShopCartDataConverter().setJsonData(response).convert();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mAdapter = new ShopCartAdapter(data);
	    mAdapter.setCartItemListener(this);
        final ItemTouchHelper.Callback callback = new ShopCartItemTouchHelperCallback(mAdapter);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        checkItemCount();
        final double totalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(totalPrice));
    }


	@Override
	public void checkItemCount() {
		final int totalCount = mAdapter.getItemCount();
		if (totalCount == 0) {
			//购物车中没有商品
			@SuppressLint("RestrictedApi")
			//去购物 UI显示
			final View stubView = mStubNoItem.inflate();
			final AppCompatTextView stubToBuy = (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
			stubToBuy.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getSupportDelegate().start(new IndexDelegate());
				}
			});
			mRecyclerView.setVisibility(View.GONE);
		} else {
			mRecyclerView.setVisibility(View.VISIBLE);
		}
	}

    @Override
    public void onItemClick(double totalPrice) {
        final String price = String.valueOf(totalPrice);
        mTvTotalPrice.setText(price);
    }

}
