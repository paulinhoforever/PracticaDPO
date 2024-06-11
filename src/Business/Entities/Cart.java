package Business.Entities;

import java.util.ArrayList;

/**
 * The Cart class represents a shopping cart that contains a list of products.
 *
 */
public class Cart extends Products {

    /**
     * An ArrayList to store the products in the cart.
     */
    private ArrayList<Products> products;

    /**
     * Constructs an empty Cart with an initialized ArrayList to store products.
     */
    public Cart() {
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to the cart.
     *
     * @param product The product to be added to the cart.
     */
    public void addToCart(Products product) {
        products.add(product);
    }

    /**
     * Retrieves the list of products in the cart.
     *
     * @return An ArrayList containing the products in the cart.
     */
    public ArrayList<Products> getProducts() {
        return products;
    }

    /**
     * Sets the list of products in the cart.
     *
     * @param products The ArrayList of products to set in the cart.
     */
    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }
}
