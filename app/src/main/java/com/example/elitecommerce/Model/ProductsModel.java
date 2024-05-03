package com.example.elitecommerce.Model;

public class ProductsModel {
    String _id;
    String productImage;
    String productTitle;
    double productPrice;
    double productRatings;
    int productReviews;
    String productCategory;

    public ProductsModel(String _id, String productImage, String productTitle, double productPrice, double productRatings, int productReviews, String productCategory) {
        this._id = _id;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productRatings = productRatings;
        this.productReviews = productReviews;
        this.productCategory = productCategory;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductRatings() {
        return productRatings;
    }

    public void setProductRatings(double productRatings) {
        this.productRatings = productRatings;
    }

    public int getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(int productReviews) {
        this.productReviews = productReviews;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
