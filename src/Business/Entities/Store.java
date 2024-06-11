package Business.Entities;

import java.util.ArrayList;

/**
 * The `Store` class represents a type of store.
 */
public class Store {

    private ArrayList<Products> products;
    private String name;
    private String description;
    private int yearOfFoundation;
    private float earnings;
    private String category;
    private ArrayList<Products> catalogue;


    /**
     * Default constructor for the Store class.
     * This constructor initializes a new instance of the Store class.
     * It is typically used when creating a Store object with default or
     * uninitialized values, which can be set later using setters.
     */
    public Store() {
        // Default constructor with no initial actions
    }


    /**
     * Constructs a new Store instance with the specified attributes.
     *
     * @param name              The name of the store.
     * @param description       The description of the store.
     * @param year_of_foundation The year the store was founded.
     * @param earnings          The earnings of the store.
     * @param category          The category of the store.
     * @param catalogue         The catalogue of products offered by the store.
     */
    public Store(String name, String description, int year_of_foundation, float earnings, String category, ArrayList<Products> catalogue) {
        this.name = name;
        this.description = description;
        this.yearOfFoundation = year_of_foundation;
        this.earnings = earnings;
        this.category = category;
        this.catalogue = catalogue;
    }
    /**
     * Retrieves the name of the store.
     *
     * @return The name of the store.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the description of the store.
     *
     * @return The description of the store.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the list of products associated with the store.
     *
     * @return The list of products associated with the store.
     */
    public ArrayList<Products> getProducts() {
        return products;
    }

    /**
     * Sets the list of products associated with this store.
     *
     * @param products The list of products to be associated with this store.
     */
    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    /**
     * Retrieves the earnings of the store.
     *
     * @return The earnings of the store.
     */
    public float getEarnings() {
        return earnings;
    }

    /**
     * Sets the earnings of the store.
     *
     * @param earnings The earnings of the store.
     */
    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    /**
     * Retrieves the catalogue of products offered by the store.
     *
     * @return The catalogue of products offered by the store.
     */
    public ArrayList<Products> getCatalogue() {
        return catalogue;
    }

    /**
     * Sets the catalogue of products offered by the store.
     *
     * @param catalogue The catalogue of products offered by the store.
     */
    public void setCatalogue(ArrayList<Products> catalogue) {
        this.catalogue = catalogue;
    }

    /**
     * Retrieves the year the store was founded.
     *
     * @return The year the store was founded.
     */
    public int getYearOfFoundation() {
        return yearOfFoundation;
    }
}


