package com.turhan.fruitshop.catalog;

import com.turhan.fruitshop.models.ProductOrder;

import java.util.ArrayList;
import java.util.HashSet;

public interface CatalogMvp {
    interface Model {
        void fetchCatalog();

        void addProductToBasket(ProductOrder product);

        void removeProductFromBasket(ProductOrder product);

        HashSet<ProductOrder> getBasket();
    }

    interface View {
        void showOnLoading();

        void showOnError();

        void showProducts(ArrayList<ProductOrder> products);

        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void init();

        void onError();

        void onCatalogReceived(ArrayList<ProductOrder> productOrders);

        void onProductAdded(ProductOrder product);

        void onProductRemoved(ProductOrder product);

        void onBasketClicked();
    }

}
