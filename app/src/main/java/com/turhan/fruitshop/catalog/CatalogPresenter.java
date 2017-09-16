package com.turhan.fruitshop.catalog;

import android.app.Activity;
import android.widget.Toast;

import com.turhan.fruitshop.R;
import com.turhan.fruitshop.basket.BasketActivity;
import com.turhan.fruitshop.models.ProductOrder;

import java.util.ArrayList;

public class CatalogPresenter implements CatalogMvp.Presenter {
    Activity activity;
    CatalogMvp.View view;
    CatalogMvp.Model model;

    public CatalogPresenter(Activity activity, CatalogMvp.View view) {
        this.activity = activity;
        this.view = view;
        this.model = new CatalogModel(this);

        this.view.setPresenter(this);
    }

    @Override
    public void init() {
        view.showOnLoading();
        model.fetchCatalog();
    }

    @Override
    public void onProductAdded(ProductOrder product) {
        model.addProductToBasket(product);
    }

    @Override
    public void onProductRemoved(ProductOrder product) {
        model.removeProductFromBasket(product);
    }

    @Override
    public void onBasketClicked() {
        if(model.getBasket().size() == 0){
            Toast.makeText(activity, R.string.basket_empty, Toast.LENGTH_SHORT).show();
        }
        else {
            BasketActivity.start(activity, model.getBasket());
        }
    }

    @Override
    public void onError() {
        view.showOnError();
    }

    @Override
    public void onCatalogReceived(ArrayList<ProductOrder> products) {
        view.showProducts(products);
    }
}
