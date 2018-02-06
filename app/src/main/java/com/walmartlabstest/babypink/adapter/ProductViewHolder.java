package com.walmartlabstest.babypink.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.walmartlabstest.babypink.BabyPinkApp;
import com.walmartlabstest.babypink.ProductActivity;
import com.walmartlabstest.babypink.R;
import com.walmartlabstest.babypink.model.Product;

/**
 * Created by srikaram on 06-Feb-18.
 */

class ProductViewHolder extends RecyclerView.ViewHolder {

    private ImageView mProductImage;
    private TextView mProductCost;
    private TextView mProductDescription;

    ProductViewHolder(View itemView) {
        super(itemView);
        mProductImage = itemView.findViewById(R.id.product_imageview);
        mProductDescription = itemView.findViewById(R.id.product_description_tv);
        mProductCost = itemView.findViewById(R.id.product_cost_tv);
    }

    void bind(final Product product) {
        BabyPinkApp.getApp(mProductImage.getContext()).getService().loadImageView(mProductImage,
                String.valueOf(product.getProductId()));
        mProductCost.setText("$"+product.getFinalPrice());
        mProductDescription.setText(product.getDescription());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mProductImage.getContext(), ProductActivity.class);
                intent.putExtra("Product", product);
                mProductImage.getContext().startActivity(intent);
            }
        });
    }
}
