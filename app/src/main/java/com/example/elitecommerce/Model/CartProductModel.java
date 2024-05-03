package com.example.elitecommerce.Model;

public class CartProductModel {

    private double productPrice, productRatings;
            private int productReviews, productQuantity;
   private String _id , productId, productImage, productTitle, productCategory, userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public CartProductModel(String productId, String productImage, String productTitle, double productPrice, double productRatings, int productReviews, String productCategory, int productQuantity, String userEmail) {
        this.productId = productId;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productRatings = productRatings;
        this.productReviews = productReviews;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
        this.userEmail = userEmail;
    }

    public CartProductModel(String _id, String productId, String productImage, String productTitle, double productPrice, double productRatings, int productReviews, String productCategory, int productQuantity, String userEmail) {
        this._id = _id;
        this.productId = productId;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productRatings = productRatings;
        this.productReviews = productReviews;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
        this.userEmail = userEmail;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
