package Persistence;

import Business.Entities.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class productsJSONDAO {

    private final String jsonFilePath = "C:\\Users\\Toni\\ClaseOOPP\\PracticaDPO\\Files\\products.json";
    private final FileReader fr;

    public productsJSONDAO() throws FileNotFoundException {
        URL resource = ClassLoader.getSystemClassLoader().getResource(jsonFilePath);
        fr = new FileReader(resource.getFile());
    }

    public Products readJsonFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(jsonFilePath)) {
            // Use Gson to parse the JSON file into a Product object
            return gson.fromJson(reader, Products.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeJsonFile(Products product) {
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            // Use Gson to convert the Product object back to JSON and write it to the file
            new GsonBuilder().setPrettyPrinting().create().toJson(product, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
