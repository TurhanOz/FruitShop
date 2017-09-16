package com.turhan.fruitshop.catalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.turhan.fruitshop.R;

public class CatalogActivity extends AppCompatActivity {
    CatalogPresenter catalogPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        initCatalogMvp();

        catalogPresenter.init();
    }

    private void initCatalogMvp() {
        final CatalogView catalogView = findViewById(R.id.catalog_view);
        catalogPresenter = new CatalogPresenter(this, catalogView);
    }
}
