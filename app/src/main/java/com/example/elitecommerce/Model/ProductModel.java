package com.example.elitecommerce.Model;

public class ProductModel {
    public int productImage;
    public String productTitle,  productPrice, productRatings, productReviews , productCategory;

    public int getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductRatings() {
        return productRatings;
    }

    public String getProductReviews() {
        return productReviews;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductRatings(String productRatings) {
        this.productRatings = productRatings;
    }

    public void setProductReviews(String productReviews) {
        this.productReviews = productReviews;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public ProductModel(int productImage, String productTitle, String productPrice, String productRatings, String productReviews, String productCategory) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productRatings = productRatings;
        this.productReviews = productReviews;
        this.productCategory = productCategory;
    }

    public ProductModel() {

    }

    public ProductModel(int productImage, String productTitle, String productPrice, String productCategory) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productRatings = "0";
        this.productReviews = "0";
    }



    public ProductModel(int productImage, String productTitle, String productPrice, String productRatings, String productCategory) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productRatings = productRatings;
        this.productCategory = productCategory;
        this.productReviews = "0";
    }


}
