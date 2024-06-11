package Persistence;

import Business.Entities.Products;
import Business.Entities.Store;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class provides data access methods for reading and writing Store data in JSON format.
 */
public class ShopJSONDAO {

    private final String path = "data/stores.json";

    private final Gson gson;

    /**
     * Constructs a new ShopJSONDAO instance and initializes Gson.
     */
    public ShopJSONDAO() {
        this.gson = new Gson();
    }

    /**
     * Reads the JSON file and returns a list of Stores.
     *
     * @return The list of Stores read from the JSON file.
     */
    public ArrayList<Store> readStores() {
        try {
            String fileContent = Files.readString(Paths.get(path));
            Type storeListType = new TypeToken<ArrayList<Store>>() {}.getType();
            return new Gson().fromJson(fileContent, storeListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Store>();
        }
    }

    /**
     * Writes the given list of Stores to the JSON file.
     *
     * @param stores The list of Stores to be written to the JSON file.
     */
    public void writeStores(ArrayList<Store> stores) {
        try (FileWriter writer = new FileWriter(path)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(stores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new Store to the JSON file.
     *
     * @param newStore The new Store to be added.
     */
    public void addStore(Store newStore) {
        ArrayList<Store> existingStores = readStores();
        if (existingStores == null) {
            existingStores = new ArrayList<>();
        }

        existingStores.add(newStore);
        writeStores(existingStores);
    }

    /**
     * Adds a new Product to the specified Store's catalogue in the JSON file.
     *
     * @param storeName  The name of the Store.
     * @param newProduct The new Product to be added to the catalogue.
     */
    public void addProductToStore(String storeName, Products newProduct) {
        ArrayList<Store> existingStores = readStores();
        if (existingStores != null) {
            for (Store store : existingStores) {
                if (store.getName().equals(storeName)) {
                    if (store.getCatalogue() == null) {
                        store.setCatalogue(new ArrayList<>());
                    }
                    store.getCatalogue().add(newProduct);
                    break;
                }
            }
            writeStores(existingStores);
        }
    }

    /**
     * Removes a Product from the specified Store's catalogue in the JSON file.
     *
     * @param storeName The name of the Store.
     * @param idProduct The ID of the Product to be removed.
     */
    public void removeProductFromStore(String storeName, int idProduct) {
        ArrayList<Store> existingStores = readStores();
        if (existingStores != null) {
            for (Store store : existingStores) {
                if (store.getName().equals(storeName)) {
                    if (store.getCatalogue() != null && idProduct >= 0) {
                        for(int i = 0; i < store.getCatalogue().size(); i++){
                            if(store.getCatalogue().get(i).getId() == idProduct){
                                System.out.println(store.getCatalogue().get(i).getName() + "     " + store.getCatalogue().get(i).getId() + "  " + idProduct);
                                store.getCatalogue().remove(i);
                            }
                        }
                        break;
                    }
                }
            }
            writeStores(existingStores);
        }
    }
}
