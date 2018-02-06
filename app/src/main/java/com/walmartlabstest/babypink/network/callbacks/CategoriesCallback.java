package com.walmartlabstest.babypink.network.callbacks;

import com.walmartlabstest.babypink.model.Categories;

/**
 * Created by srikaram on 06-Feb-18.
 */

public interface CategoriesCallback {
    void onCategoriesFetched(Categories categories);
    void onError(Exception e);
}
