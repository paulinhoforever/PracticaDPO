package Business.Managers;

import Business.Entities.Cart;
import Business.Entities.Products;
import java.util.ArrayList;


/**
 * Manages the operations related to the shopping cart.
 * This class provides methods to add products to the cart,
 * list the products in the cart, calculate the total cost of the cart,
 * and clear the cart.
 */
public class CartManager {
private final Cart cart;

    /**
     * Constructs a new {@code CartManager} with the specified shopping cart.
     *
     * @param cart The shopping cart to be managed.
     */
    public CartManager(Cart cart) {
        this.cart = cart;
    }

    /**
     * Adds the specified product to the shopping cart.
     *
     * @param products The product to be added to the shopping cart.
     */

    public void addToCart(Products products){
        cart.addToCart(products);
    }

    /**
     * Retrieves the list of products currently in the shopping cart.
     *
     * @return An ArrayList containing the products in the shopping cart.
     */

    public ArrayList<Products> listProductsCart(){
        return cart.getProducts();
    }

    /**
     * Calculates the total cost of the products in the shopping cart.
     *
     * @param products The list of products in the shopping cart.
     * @return The total cost of the products in the shopping cart.
     */
    public float costCart(ArrayList<Products> products){
        float total = 0;
        for (Products product : products) {
            total = total + product.getPrice();
        }
        return total;
    }

    /**
     * Clears the cart by removing all products from it.
     *
     * @param products The list of products representing the cart.
     */

    public void eraseCart(ArrayList<Products> products){
        products.clear();
    }
}
