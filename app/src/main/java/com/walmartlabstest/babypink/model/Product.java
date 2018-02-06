package com.walmartlabstest.babypink.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by srikaram on 06-Feb-18.
 */

public class Product implements Serializable {

    @JsonProperty("product.id")
    private int productId;
    @JsonProperty("product.category.id")
    private int productCategoryId;
    @JsonProperty("product.image.url")
    private String productImageUrl;
    @JsonProperty("sku.displayName")
    private String skuDisplayName;
    @JsonProperty("product.displayName")
    private String productDisplayName;
    @JsonProperty("product.description")
    private String description;
    @JsonProperty("sku.finalPrice")
    private int finalPrice;
    @JsonProperty("sku.lastPrice")
    private int lastPrice;
    @JsonProperty("product.tag")
    private String tag;
    private float maxQuantity;
    private float priceStrikeOff;
    private int inventoryTotal;
    private int inventoryUsed;
    @JsonProperty("product.offer")
    private ProductOffer productOffer;

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPriceStrikeOff(float priceStrikeOff) {
        this.priceStrikeOff = priceStrikeOff;
    }

    public float getPriceStrikeOff() {
        return priceStrikeOff;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getSkuDisplayName() {
        return skuDisplayName;
    }

    public void setSkuDisplayName(String skuDisplayName) {
        this.skuDisplayName = skuDisplayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(int lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(float maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getInventoryTotal() {
        return inventoryTotal;
    }

    public void setInventoryTotal(int inventoryTotal) {
        this.inventoryTotal = inventoryTotal;
    }

    public int getInventoryUsed() {
        return inventoryUsed;
    }

    public void setInventoryUsed(int inventoryUsed) {
        this.inventoryUsed = inventoryUsed;
    }

    static class ProductOffer implements Serializable {
        private int buy;
        private int free;

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getFree() {
            return free;
        }

        public void setFree(int free) {
            this.free = free;
        }
    }
}
