package com.turhan.fruitshop.catalog;

import com.turhan.fruitshop.models.Product;
import com.turhan.fruitshop.models.ProductOrder;
import com.turhan.fruitshop.webservice.FruitShopService;
import com.turhan.fruitshop.webservice.WsFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CatalogModel implements CatalogMvp.Model {
    private final CatalogMvp.Presenter presenter;
    FruitShopService service;
    HashSet<ProductOrder> basket;

    public CatalogModel(CatalogMvp.Presenter presenter) {
        service = WsFactory.getWsClient().create(FruitShopService.class);
        this.presenter = presenter;
        this.basket  = new HashSet<>();
    }

    @Override
    public void fetchCatalog() {
        service.getCatalog()
                .map(toProductCounts())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());
    }

    @android.support.annotation.NonNull
    private Function<List<Product>, ArrayList<ProductOrder>> toProductCounts() {
        return new Function<List<Product>, ArrayList<ProductOrder>>() {
            @Override
            public ArrayList<ProductOrder> apply(@NonNull List<Product> products) throws Exception {
                ArrayList<ProductOrder> productOrders = new ArrayList();
                for(Product product : products){
                    ProductOrder productOrder = new ProductOrder(product);
                    productOrders.add(productOrder);
                }
                return productOrders;
            }
        };
    }

    @Override
    public void addProductToBasket(ProductOrder product) {
        basket.add(product);
    }

    @Override
    public void removeProductFromBasket(ProductOrder product) {
        if(product.getOrderCount() == 0)
            basket.remove(product);
    }

    @Override
    public HashSet<ProductOrder> getBasket() {
        return basket;
    }

    @android.support.annotation.NonNull
    private Observer<ArrayList<ProductOrder>> getObserver() {
        return new Observer<ArrayList<ProductOrder>>() {
            Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull ArrayList<ProductOrder> products) {
                presenter.onCatalogReceived(products);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                disposable.dispose();
                presenter.onError();
            }

            @Override
            public void onComplete() {
                disposable.dispose();
            }
        };
    }
}
