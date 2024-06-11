package Business.Managers;

import Business.Entities.Products;
import Exceptions.NoProductsWithTheQueryYouProvide;
import Persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages product-related operations, including checking for existing products,
 * searching for products, adding new products, and handling product reviews.
 * This class interacts with the data access layer to persist product information.
 */
public class ProductsManager {
    private final productsJSONDAO ProductsJSONDAO;
    private final Products products;

    /**
     * Constructs a new ProductsManager.
     *
     * @param ProductsJSONDAO The data access object for managing products' persistence.
     * @param products The entity representing products.
     */

    public ProductsManager(productsJSONDAO ProductsJSONDAO, Products products) {
        this.ProductsJSONDAO = ProductsJSONDAO;
        this.products = products;
    }

    /**
     * Formats the given product brand name by capitalizing the first letter of each word and converting the rest to lowercase.
     *
     * @param nombreProducto The original product brand name.
     * @return The formatted product brand name.
     */
    public String comprovarMarcaProdcuto(String nombreProducto) {
        String[] words = nombreProducto.split("\\s+");
        StringBuilder formattedBrandName = new StringBuilder();

        for (String word : words) {
            if (formattedBrandName.length() > 0) {
                formattedBrandName.append(" ");
            }
            formattedBrandName.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase());
        }
        return formattedBrandName.toString();
    }

    /**
     * Checks if a product with the given name already exists in the product list.
     *
     * @param nombreProducto The name of the product to check.
     * @return True if the product doesn't exist, false otherwise.
     */
    public boolean comprovarProductExiste(String nombreProducto) {
        int i = 0;
        ArrayList<Products> products = ProductsJSONDAO.readJsonFile();
        while (i < products.size()) {
            if (nombreProducto.equals(products.get(i).getName())) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Searches for products containing the specified query in their name or brand.
     *
     * @param query The query to search for in product names or brands.
     * @return List of products matching the query.
     * @throws NoProductsWithTheQueryYouProvide The query you provide does not exists.
     */
    public ArrayList<Products> buscarProductosQuery(String query) throws NoProductsWithTheQueryYouProvide {
        int i = 0;
        ArrayList<Products> products = ProductsJSONDAO.readJsonFile();
        ArrayList<Products> filteredProducts = new ArrayList<>();
        while (i < products.size()){
            if(products.get(i).getName().toLowerCase().contains(query.toLowerCase()) || products.get(i).getBrand().equals(query)){
                filteredProducts.add(products.get(i));
            }
            i++;
        }
        if(filteredProducts.isEmpty()){
            throw new NoProductsWithTheQueryYouProvide();
        }
        return filteredProducts;
    }

    /**
     * Searches for a product by its name in the product list.
     *
     * @param nombreProducto The name of the product to search for.
     * @return The product with the specified name, or null if not found.
     */
    public Products buscarProducto(String nombreProducto) {
        int i = 0;
        ArrayList<Products> products = ProductsJSONDAO.readJsonFile();
        while (i < products.size()) {
            if (nombreProducto.equals(products.get(i).getName())) {
                return products.get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Adds a price to the product and creates a new instance with the updated price.
     *
     * @param products The original product.
     * @param price    The new price to be added.
     * @return A new Products instance with the updated price.
     */
    public Products añadirPrecioProductCatalogo(Products products, float price) {
        return new Products(products.getName(), products.getBrand(), products.getCategorie(), products.getMaximum_retail_price(), price, products.getId());
    }

    /**
     * Adds a review to the specified product in the list of products.
     *
     * @param products The list of products where the review will be added.
     * @param index    The index of the product in the list to which the review will be added.
     * @param review   The review to be added to the product.
     */
    public void addReviewToProduct(ArrayList<Products> products, int index, String review){
        int i = 0;
        ArrayList<Products> productos;
        productos = ProductsJSONDAO.readJsonFile();

        while(productos.size() > i){
            if(productos.get(i).getId() == products.get(index - 1).getId()){
                if(productos.get(i).getReviews() == null){
                    productos.get(i).setReviews(new ArrayList<>());
                }
                   productos.get(i).getReviews().add(review);
            }
            i++;
        }
        ProductsJSONDAO.writeJsonFile(productos);
    }


    /**
     * Reads reviews of a product by its index in the products list.
     *
     * @param products An ArrayList of Products objects.
     * @param index The index of the product whose reviews to read.
     * @return A List of String objects containing the product's reviews.
     */

    public List<String> readReviewsOfProduct(ArrayList<Products> products, int index){
        return products.get(index - 1).getReviews();
    }

    /**
     * Creates a new product and adds it to the product list.
     *
     * @param nameProduct The name of the new product.
     * @param nameBrand   The brand of the new product.
     * @param category    The category of the new product.
     * @param priceMRP    The maximum retail price of the new product.
     */
    public void newProduct(String nameProduct, String nameBrand, String category, float priceMRP) {
        Products products1 = new Products(nameProduct, nameBrand, category, priceMRP, 0, getNextProductId());
        ProductsJSONDAO.addProduct(products1);
    }

    /**
     * Removes a product from the product list based on its ID and index.
     *
     * @param idProduct    The ID of the product to be removed.
     * @param indexProduct The index of the product in the list.
     */
    public void removeProduct(int idProduct, int indexProduct) {
       ProductsJSONDAO.removeProduct(idProduct, indexProduct);
    }


    /**
     * Retrieves a list of all products from the JSON data source.
     *
     * @return An ArrayList containing all the products.
     */
    public ArrayList<Products> listAllProducts() {
        return ProductsJSONDAO.readJsonFile();
    }

    /**
     * Retrieves the ID of the product at the specified position in the list.
     *
     * @param position The position of the product in the list.
     * @param products The list of products.
     * @return The ID of the product at the specified position.
     */
    public int idProduct(int position, ArrayList<Products> products) {
        return products.get(position - 1).getId();
    }

    /**
     * Retrieves the next available product ID based on the existing products.
     *
     * @return The next available product ID.
     */
    private int getNextProductId() {
        ArrayList<Products> products = ProductsJSONDAO.readJsonFile();
        return products.size() + 1;
    }


}

