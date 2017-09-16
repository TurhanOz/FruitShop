package com.turhan.fruitshop.basket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.turhan.fruitshop.R;
import com.turhan.fruitshop.models.ProductOrder;

import java.util.HashSet;

public class BasketActivity  extends AppCompatActivity {
    public static final String PRODUCTS_EXTRA = "products_extra";

    BasketPresenter presenter;

    public static void start(Activity activity, HashSet<ProductOrder> productOrders) {
        Intent intent = new Intent(activity, BasketActivity.class);
        intent.putExtra(PRODUCTS_EXTRA, productOrders);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        initBasketMvp();

        presenter.init();
    }

    private void initBasketMvp() {
        BasketView view = findViewById(R.id.basket_view);
        presenter = new BasketPresenter(this, view);
    }

}
