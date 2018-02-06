package com.walmartlabstest.babypink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.walmartlabstest.babypink.adapter.ProductAdapter;
import com.walmartlabstest.babypink.model.Categories;
import com.walmartlabstest.babypink.model.Category;
import com.walmartlabstest.babypink.model.Product;
import com.walmartlabstest.babypink.network.callbacks.CategoriesCallback;
import com.walmartlabstest.babypink.network.callbacks.ProductsCallback;
import com.walmartlabstest.babypink.views.RefreshProgressBar;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RefreshProgressBar mProgressBar;
    private Spinner mCategoriesSpinner;
    private RecyclerView mProductsList;
    List<Category> mActiveCategories;
    private ProductAdapter mProductAdapter;

    private CategoriesCallback mCategoriesCallback = new CategoriesCallback() {
        @Override
        public void onCategoriesFetched(Categories categories) {
            mActiveCategories = new ArrayList<>();
            for (Category category : categories.getContents()) {
                if (category.isActive()) {
                    mActiveCategories.add(category);
                }
            }
            String[] categoryNames = new String[mActiveCategories.size()];
            int i = 0;
            for (Category category : mActiveCategories) {
                categoryNames[i++] = category.getDisplayName();
            }
            ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(CategoriesActivity.this,
                    R.layout.spinner_dropdown_item, categoryNames);
            mCategoriesSpinner.setAdapter(categoriesAdapter);
            mCategoriesSpinner.setOnItemSelectedListener(CategoriesActivity.this);
            mProgressBar.dismiss();
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
            Toast.makeText(CategoriesActivity.this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
            mProgressBar.dismiss();
        }
    };

    private ProductsCallback mProductsCallback = new ProductsCallback() {
        @Override
        public void onProductsFetched(List<Product> products) {
            if (mProductAdapter == null) {
                mProductAdapter = new ProductAdapter(products);
                mProductsList.setAdapter(mProductAdapter);
            } else {
                mProductAdapter.setProducts(products);
            }
            mProgressBar.dismiss();
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
            Toast.makeText(CategoriesActivity.this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
            mProgressBar.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        mProgressBar = findViewById(R.id.progress);
        mCategoriesSpinner = findViewById(R.id.categories_spinner);
        mProductsList = findViewById(R.id.categories_list);
        mProductsList.setLayoutManager(new GridLayoutManager(this, 2));

        BabyPinkApp.getApp(this).getService().getCategoriesList(mCategoriesCallback);
        mProgressBar.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Category category = mActiveCategories.get(i);
        BabyPinkApp.getApp(this).getService().getProducts(String.valueOf(category.getId()), mProductsCallback);
        mProgressBar.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
