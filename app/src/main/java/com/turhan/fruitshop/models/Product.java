package com.turhan.fruitshop.models;

import android.support.annotation.Keep;

import java.io.Serializable;

@Keep
@org.parceler.Parcel
public class Product implements Serializable {
    public String ref;
    public String title;
    public String description;
    public String thumbnail;
    public long price;

    @Override
    public String toString() {
        return "Product{" +
                "ref='" + ref + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        if (ref != null ? !ref.equals(product.ref) : product.ref != null) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        return thumbnail != null ? thumbnail.equals(product.thumbnail) : product.thumbnail == null;

    }

    @Override
    public int hashCode() {
        int result = ref != null ? ref.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }
}
