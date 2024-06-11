package Business.Entities;

import java.util.List;

/**
 * The `Products` class represents a type of product. Contains a list of products.
 */
public class Products {
    private String name;
    private String brand;
    private float mrp;
    private String category;
    private float price;
    private int id;

    private List<String> reviews;

    /**
     * Constructs a new Products object with default values.
     */
    public Products(){
    }

    /**
     * Constructs a new Products object with specified values for name, brand, maximum retail price, category, and reviews.
     *
     * @param name                The name of the product.
     * @param brand               The brand of the product.
     * @param maximum_retail_price The maximum retail price of the product.
     * @param category            The category of the product.
     * @param reviews             The list of reviews for the product.
     * @throws IllegalArgumentException if an invalid category is provided.
     */
    public Products(String name, String brand, float maximum_retail_price, String category, List<String> reviews) {
        this.name = name;
        this.brand = brand;
        this.mrp = maximum_retail_price;
        this.category = category;
        this.reviews = reviews;

        if (isValidCategory(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    /**
     * Constructs a new Products object with specified values for name, brand, category, maximum retail price, price, and ID.
     *
     * @param name     The name of the product.
     * @param brand    The brand of the product.
     * @param category The category of the product.
     * @param mrp      The maximum retail price of the product.
     * @param price    The price of the product.
     * @param id       The ID of the product.
     */
    public Products(String name, String brand, String category, float mrp,float price, int id){
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.mrp = mrp;
        this.price = price;
        this.id = id;
    }

    /**
     * Retrieves the ID of the product.
     *
     * @return The price of the product.
     */

    public int getId(){
        return id;
    }

    /**
     * Sets the ID of the product.
     * @param id id of the product.
     */

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Checks if a given category is valid.
     *
     * @param category The category to be checked.
     * @return true if the category is valid, false otherwise.
     */

    private boolean isValidCategory(String category) {
        return category.equals("GENERAL") || category.equals("REDUCED") || category.equals("SUPER_REDUCED");
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the brand of the product.
     *
     * @return The brand of the product.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Retrieves the maximum retail price of the product.
     *
     * @return The maximum retail price of the product.
     */
    public float getMaximum_retail_price() {
        return mrp;
    }

    /**
     * Retrieves the category of the product.
     *
     * @return The category of the product.
     */
    public String getCategorie() {
        return category;
    }

    /**
     * Retrieves the list of reviews for the product.
     *
     * @return The list of reviews for the product.
     */
    public List<String> getReviews() {
        return reviews;
    }

    /**
     * Sets the list of reviews for the product.
     *
     * @param reviews The list of reviews to be set.
     */
    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
