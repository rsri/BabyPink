package com.walmartlabstest.babypink.network;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walmartlabstest.babypink.model.Categories;
import com.walmartlabstest.babypink.network.callbacks.CategoriesCallback;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

/**
 * Created by srikaram on 06-Feb-18.
 */

class CategoriesRequest {

    private static final String TAG = CategoriesRequest.class.getName();

    private CategoriesCallback categoriesCallback;
    private final Response.Listener<String> mSuccessListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                Log.i(TAG, "onResponse() " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                Categories categories = objectMapper.readValue(response, new TypeReference<Categories>() {
                });
                if (categoriesCallback != null) {
                    categoriesCallback.onCategoriesFetched(categories);
                }
            } catch (IOException e) {
                e.printStackTrace();
                categoriesCallback.onError(e);
            }
        }
    };

    private final Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (categoriesCallback != null) {
                categoriesCallback.onError(error);
            }
        }
    };

    CategoriesRequest setCategoriesCallback(CategoriesCallback categoriesCallback) {
        this.categoriesCallback = categoriesCallback;
        return this;
    }

    StringRequest getRequest() {
        return new StringRequest(NetworkURLs.CATEGORIES_LIST, mSuccessListener, mErrorListener);
    }
}
