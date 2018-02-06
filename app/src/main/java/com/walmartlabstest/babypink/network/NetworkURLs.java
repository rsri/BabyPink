package com.walmartlabstest.babypink.network;

/**
 * Created by srikaram on 06-Feb-18.
 */

class NetworkURLs {

    private static final String BASE_URL = "http://static-data.surge.sh/";

    static final String CATEGORIES_LIST = BASE_URL + "categories.json";

    static final String PRODUCT_LIST = BASE_URL + "products/products.%s.json";

    static final String PRODUCT_IMAGE = BASE_URL + "/assets/categories/%s.png";
}
