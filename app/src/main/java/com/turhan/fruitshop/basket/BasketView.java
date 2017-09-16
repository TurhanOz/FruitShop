package com.turhan.fruitshop.basket;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.turhan.fruitshop.R;
import com.turhan.fruitshop.models.ProductOrder;

import java.util.ArrayList;

public class BasketView extends ConstraintLayout implements BasketMvp.View {
    TextView infoView;
    TextView priceView;
    RecyclerView contentView;
    FloatingActionButton fab;
    BasketMvp.Presenter presenter;

    public BasketView(Context context) {
        super(context);
        initUi();
    }

    public BasketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi();
    }

    public BasketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi();

    }

    private void initUi() {
        View root = inflate(getContext(), R.layout.view_basket, this);
        infoView = root.findViewById(R.id.info_view);
        priceView = root.findViewById(R.id.price_view);
        contentView = root.findViewById(R.id.content_view);
        contentView.setLayoutManager(new LinearLayoutManager(getContext()));

        fab = root.findViewById(R.id.action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPresenter();
                presenter.onPurchaseClicked();
            }
        });
    }

    @Override
    public void setPresenter(BasketMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showOnLoading() {
        showMessage(R.string.waiting_content);
    }

    @Override
    public void showOnError() {
        showMessage(R.string.error_content);
    }

    private void showMessage(@StringRes int messageId) {
        contentView.setVisibility(GONE);
        priceView.setVisibility(GONE);
        fab.setVisibility(GONE);
        infoView.setText(messageId);
        infoView.setVisibility(VISIBLE);
    }

    @Override
    public void showProducts(ArrayList<ProductOrder> products) {
        contentView.setVisibility(VISIBLE);
        priceView.setVisibility(VISIBLE);
        fab.setVisibility(VISIBLE);
        infoView.setVisibility(GONE);

        BasketAdapter adapter = new BasketAdapter(products);
        contentView.setAdapter(adapter);
    }

    @Override
    public void showPrice(String price) {
        priceView.setText(price);
    }

    private void checkPresenter() {
        if (presenter == null)
            throw new IllegalArgumentException("you should set a Presenter first");
    }
}
