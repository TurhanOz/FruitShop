package com.turhan.fruitshop.basket;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turhan.fruitshop.R;
import com.turhan.fruitshop.models.Product;
import com.turhan.fruitshop.models.ProductOrder;

import java.util.ArrayList;

import static android.view.View.GONE;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    private ArrayList<ProductOrder> products;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView title;
        public TextView description;
        public TextView price;
        public TextView count;
        public Button add;
        public Button remove;

        public ViewHolder(View v) {
            super(v);
            picture = v.findViewById(R.id.picture);
            title = v.findViewById(R.id.title);
            description = v.findViewById(R.id.description);
            price = v.findViewById(R.id.price);
            count = v.findViewById(R.id.count);
            add = v.findViewById(R.id.add);
            remove = v.findViewById(R.id.remove);
        }
    }

    public BasketAdapter(ArrayList<ProductOrder> products) {
        this.products = products;
    }


    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_product, parent, false);
        ViewHolder vh = new ViewHolder(root);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProductOrder productOrder = products.get(position);
        Product product = productOrder.product;
        holder.title.setText(product.title);
        holder.description.setText(product.description);
        holder.price.setText(productOrder.getDisplayPrice());
        Glide.with(holder.picture.getContext())
                .load(product.thumbnail)
                .into(holder.picture);
        holder.count.setText(String.valueOf(productOrder.getOrderCount()));
        holder.add.setVisibility(GONE);
        holder.remove.setVisibility(GONE);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}


