package com.walmartlabstest.babypink.network;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.walmartlabstest.babypink.network.callbacks.CategoriesCallback;
import com.walmartlabstest.babypink.network.callbacks.ProductsCallback;

/**
 * Created by srikaram on 06-Feb-18.
 */

public class Service {

    private final Context mContext;
    private final RequestQueue mRequestQueue;

    private static RetryPolicy DEFAULT_RETRY_POLICY = new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public Service(Context context) {
        if (context == null) throw new NullPointerException("context cannot be null");
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public void getCategoriesList(CategoriesCallback callback) {
        StringRequest request = new CategoriesRequest()
                .setCategoriesCallback(callback)
                .getRequest();
        post(request);
    }

    public void getProducts(String categoryId, ProductsCallback productsCallback) {
        StringRequest request = new ProductsRequest()
                .setCategoryId(categoryId)
                .setProductsCallback(productsCallback)
                .getRequest();
        post(request);
    }

    private void post(StringRequest request) {
        request.setRetryPolicy(DEFAULT_RETRY_POLICY);
        mRequestQueue.add(request);
    }

    public void loadImageView(ImageView productImage, String productId) {
        Ion.with(productImage).load(String.format(NetworkURLs.PRODUCT_IMAGE, productId));
    }
}
