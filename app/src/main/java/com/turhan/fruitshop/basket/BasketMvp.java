package com.turhan.fruitshop.basket;

import com.turhan.fruitshop.models.ProductOrder;

import java.util.ArrayList;
import java.util.HashSet;

public interface BasketMvp {
    interface Model {
        void setBasket(HashSet<ProductOrder> productOrders);

        long getBillPriceInCent();
    }

    interface View {
        void setPresenter(Presenter presenter);

        void showOnLoading();

        void showOnError();

        void showProducts(ArrayList<ProductOrder> products);

        void showPrice(String price);
    }

    interface Presenter {
        void init();

        void onError();

        void onBasketDetailReceived(ArrayList<ProductOrder> productOrders);

        void onPurchaseClicked();
    }
}
