package com.turhan.fruitshop.models;

import android.support.annotation.Keep;

import com.turhan.fruitshop.utils.PriceConvertor;

import java.io.Serializable;

@Keep
@org.parceler.Parcel
public class ProductOrder implements Serializable {
    public final Product product;
    int orderCount;

    public ProductOrder(Product product) {
        this.product = product;
    }

    public void add() {
        orderCount += 1;
    }

    public void remove() {
        orderCount--;
        if (orderCount < 0)
            orderCount = 0;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public long getOrderPriceCent() {
        return orderCount * product.price;
    }

    public String getDisplayPrice() {
        return PriceConvertor.getDisplayPrice(product.price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOrder that = (ProductOrder) o;

        return product != null ? product.equals(that.product) : that.product == null;

    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProductCount{" +
                "product=" + product +
                ", orderCount=" + orderCount +
                '}';
    }
}
