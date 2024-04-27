package com.example.elitecommerce.Model;

public class CartProductModel {
    public int productImage;
    public String productTitle,  productPrice, cartItemQuantity;

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setCartItemQuantity(String cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getCartItemQuantity() {
        return cartItemQuantity;
    }

    public CartProductModel(int productImage, String productTitle, String productPrice, String cartItemQuantity) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.cartItemQuantity = cartItemQuantity;
    }

    public CartProductModel() {
    }
}
