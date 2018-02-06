package com.walmartlabstest.babypink.network.callbacks;

import com.walmartlabstest.babypink.model.Product;

import java.util.List;

/**
 * Created by srikaram on 06-Feb-18.
 */

public interface ProductsCallback {
    void onProductsFetched(List<Product> products);
    void onError(Exception e);
}
