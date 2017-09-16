package com.turhan.fruitshop.utils;

public class PriceConvertor {
    public static String getDisplayPrice(long priceInCent){
        long dozen = priceInCent / 100;
        long cent = priceInCent - dozen * 100;

        if (cent > 0)
            return dozen + "," + cent + " €";
        else
            return dozen + "," + cent + "0 €";
    }
}
