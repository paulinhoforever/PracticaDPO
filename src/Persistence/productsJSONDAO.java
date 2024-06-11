package Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Business.Entities.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * This class provides data access methods for reading and writing Products data in JSON format.
 */
public class productsJSONDAO {
    private final String path = "data/products.json";

    private final Gson gson;

    /**
     * Constructs a new ProductsJSONDAO instance and initializes Gson.
     */
    public productsJSONDAO() {
        this.gson = new Gson();
    }

    /**
     * Reads the JSON file and returns a list of Products.
     *
     * @return The list of Products read from the JSON file.
     */
    public ArrayList<Products> readJsonFile() {
        ArrayList<Products> products = new ArrayList<>();
        try {
            if (Files.exists(Paths.get(path)) && Files.size(Paths.get(path)) > 0) {
                try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
                    Type productListType = new TypeToken<ArrayList<Products>>() {}.getType();
                    products = gson.fromJson(reader, productListType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Writes the given list of Products to the JSON file.
     *
     * @param products The list of Products to be written to the JSON file.
     */
    public void writeJsonFile(List<Products> products) {
        try (FileWriter writer = new FileWriter(path)) {
            Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
            gsonPretty.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new Product to the JSON file.
     *
     * @param newProduct The new Product to be added.
     */
    public void addProduct(Products newProduct) {
        List<Products> existingProducts = readJsonFile();
        existingProducts.add(newProduct);
        writeJsonFile(existingProducts);
    }

    /**
     * Removes a Product from the JSON file based on the product ID and updates the IDs of remaining products.
     *
     * @param idProduct  The ID of the Product to be removed.
     * @param posicion   The position of the Product to be removed in the list.
     */
    public void removeProduct(int idProduct, int posicion) {
        ArrayList<Products> existingProducts = readJsonFile();

        if(idProduct > existingProducts.size()){
        } else {
            for (int j = 0; j < existingProducts.size(); j++) {
                if (existingProducts.get(j).getId() == idProduct) {
                    existingProducts.remove(j);
                }
            }

            for (int i = (posicion - 1); i < existingProducts.size(); i++) {
                existingProducts.get(i).setId(i + 1);
            }
            writeJsonFile(existingProducts);
        }
    }
}
