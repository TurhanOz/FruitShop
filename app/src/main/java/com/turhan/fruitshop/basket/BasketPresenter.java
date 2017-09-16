package com.turhan.fruitshop.basket;

import android.app.Activity;
import android.widget.Toast;

import com.turhan.fruitshop.R;
import com.turhan.fruitshop.models.ProductOrder;
import com.turhan.fruitshop.utils.PriceConvertor;

import java.util.ArrayList;
import java.util.HashSet;

import static com.turhan.fruitshop.basket.BasketActivity.PRODUCTS_EXTRA;

public class BasketPresenter implements BasketMvp.Presenter {
    Activity activity;
    BasketMvp.View view;
    BasketMvp.Model model;

    public BasketPresenter(Activity activity, BasketMvp.View view) {
        this.activity = activity;
        this.view = view;
        this.view.setPresenter(this);

        this.model = new BasketModel(this);
    }

    @Override
    public void init() {
        view.showOnLoading();

        HashSet<ProductOrder> productOrders = (HashSet<ProductOrder>) activity.getIntent().getSerializableExtra(PRODUCTS_EXTRA);
        model.setBasket(productOrders);
    }

    @Override
    public void onError() {
        view.showOnError();
    }

    @Override
    public void onBasketDetailReceived(ArrayList<ProductOrder> productOrders) {
        this.view.showPrice(PriceConvertor.getDisplayPrice(model.getBillPriceInCent()));
        this.view.showProducts(productOrders);
    }

    @Override
    public void onPurchaseClicked() {
        Toast.makeText(activity, R.string.purchase, Toast.LENGTH_SHORT).show();
    }
}
