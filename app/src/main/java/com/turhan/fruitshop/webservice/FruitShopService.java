package com.turhan.fruitshop.webservice;

import com.turhan.fruitshop.models.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FruitShopService {
    @GET("catalog")
    Observable<List<Product>> getCatalog();
}
