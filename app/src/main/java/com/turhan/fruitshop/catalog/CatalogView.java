package com.turhan.fruitshop.catalog;

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

public class CatalogView extends ConstraintLayout implements CatalogMvp.View {
    TextView infoView;
    RecyclerView contentView;
    FloatingActionButton fab;
    CatalogMvp.Presenter presenter;

    public CatalogView(Context context) {
        super(context);
        initUi();
    }

    public CatalogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi();
    }

    public CatalogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi();

    }

    private void initUi() {
        View root = inflate(getContext(), R.layout.view_catalog, this);
        infoView = root.findViewById(R.id.info_view);
        contentView = root.findViewById(R.id.content_view);
        contentView.setLayoutManager(new LinearLayoutManager(getContext()));

        fab = root.findViewById(R.id.action_button);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPresenter();
                presenter.onBasketClicked();
            }
        });
    }

    @Override
    public void showOnLoading() {
        showMessage(R.string.waiting_content);
    }

    @Override
    public void showOnError() {
        showMessage(R.string.error_content);
    }

    private void showMessage(@StringRes int messageId){
        contentView.setVisibility(GONE);
        fab.setVisibility(GONE);
        infoView.setText(messageId);
        infoView.setVisibility(VISIBLE);
    }

    @Override
    public void showProducts(ArrayList<ProductOrder> products) {
        contentView.setVisibility(VISIBLE);
        fab.setVisibility(VISIBLE);
        infoView.setVisibility(GONE);

        CatalogAdapter adapter = new CatalogAdapter(products, presenter);
        contentView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(CatalogMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    private void checkPresenter(){
        if(presenter == null)
            throw new IllegalArgumentException("you should set a Presenter first");
    }
}
