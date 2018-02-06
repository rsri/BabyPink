package com.walmartlabstest.babypink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.walmartlabstest.babypink.model.Product;

public class ProductActivity extends AppCompatActivity {

    private ImageView mProductImage;
    private TextView mProductCost;
    private TextView mProductDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mProductImage = findViewById(R.id.product_imageview);
        mProductDescription = findViewById(R.id.product_description_tv);
        mProductCost = findViewById(R.id.product_cost_tv);

        Product product = (Product) getIntent().getSerializableExtra("Product");
        BabyPinkApp.getApp(this).getService().loadImageView(mProductImage,
                String.valueOf(product.getProductId()));
        mProductCost.setText("$"+product.getFinalPrice());
        mProductDescription.setText(product.getDescription());
    }
}
