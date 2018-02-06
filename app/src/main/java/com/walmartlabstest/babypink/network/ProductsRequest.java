package com.walmartlabstest.babypink.network;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walmartlabstest.babypink.model.Categories;
import com.walmartlabstest.babypink.model.Product;
import com.walmartlabstest.babypink.network.callbacks.CategoriesCallback;
import com.walmartlabstest.babypink.network.callbacks.ProductsCallback;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by srikaram on 06-Feb-18.
 */

class ProductsRequest {

    private static final String TAG = ProductsRequest.class.getName();

    private ProductsCallback productsCallback;
    private String categoryId;
    private final Response.Listener<String> mSuccessListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                Log.i(TAG, "onResponse() " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response);
                Iterator<JsonNode> records = jsonNode.get("records").get(0).get("attributes").get("records").iterator();
                List<Product> products = new ArrayList<>();
                while (records.hasNext()) {
                    JsonNode node = records.next();
                    Product product = objectMapper.readValue(node, new TypeReference<Product>() {
                    });
                    products.add(product);
                }
                if (productsCallback != null) {
                    productsCallback.onProductsFetched(products);
                }
            } catch (IOException e) {
                e.printStackTrace();
                productsCallback.onError(e);
            }
        }
    };

    private final Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (productsCallback != null) {
                productsCallback.onError(error);
            }
        }
    };

    ProductsRequest setProductsCallback(ProductsCallback productsCallback) {
        this.productsCallback = productsCallback;
        return this;
    }

    ProductsRequest setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    StringRequest getRequest() {
        return new StringRequest(String.format(NetworkURLs.PRODUCT_LIST, categoryId), mSuccessListener, mErrorListener);
    }
}
