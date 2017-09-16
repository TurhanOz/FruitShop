package com.turhan.fruitshop.basket;

import com.turhan.fruitshop.models.ProductOrder;

import java.util.ArrayList;
import java.util.HashSet;

public class BasketModel implements BasketMvp.Model {
    BasketMvp.Presenter presenter;
    ArrayList<ProductOrder> products;

    public BasketModel(BasketMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setBasket(HashSet<ProductOrder> productOrders) {
        products = new ArrayList<ProductOrder>(productOrders);
        presenter.onBasketDetailReceived(products);
    }

    @Override
    public long getBillPriceInCent() {
        long billPrice = 0;

        if (products == null)
            return billPrice;
        else {
            for (ProductOrder product : products) {
                billPrice += product.getOrderPriceCent();
            }

            return billPrice;
        }
    }
}
