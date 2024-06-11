package Business.Managers;

import Business.Entities.Products;
import Business.Entities.Store;
import Exceptions.NoProductsInCatalogue;
import Exceptions.StoreNameAlreadyExists;
import Exceptions.StoreNameDoesNotExist;
import Persistence.ShopJSONDAO;

import java.util.ArrayList;

/**
 * Manages store-related operations, including adding products to store catalogs,
 * creating new stores, and managing store inventories. This class interacts with
 * the data access layer to persist store information.
 */
public class StoreManager {

    private final Store stores;
    private final Products products;
    private final ShopJSONDAO shopJSONDAO;

    /**
     * Constructs a new StoreManager with the specified dependencies.
     *
     * @param shopJSONDAO The data access object for store-related operations.
     * @param products    The manager for product-related operations.
     * @param stores      The store entity representing the manager's state.
     */
    public StoreManager(ShopJSONDAO shopJSONDAO, Products products, Store stores) {
        this.shopJSONDAO = shopJSONDAO;
        this.products = products;
        this.stores = stores;
    }

    /**
     * Adds a product to a store's catalog if the product's maximum retail price is less than or equal to its price.
     *
     * @param nameStore The name of the store to which the product will be added.
     * @param products  The product to be added to the store's catalog.
     * @return True if the product is successfully added, false otherwise.
     */
    public boolean addProductToStore(String nameStore, Products products){
            if(products.getMaximum_retail_price() < products.getPrice()){
                 return false;
            }else{
                shopJSONDAO.addProductToStore(nameStore, products);
                return true;
            }
    }



    /**
     * Retrieves the list of products in a store's catalog.
     *
     * @param store The store for which to retrieve the catalog.
     * @return The list of products in the store's catalog.
     */
    public ArrayList<Products> showProductsOfStore(Store store){
        return store.getCatalogue();
    }

    /**
     * Checks if a store with the specified name exists.
     *
     * @param nameStore The name of the store to check.
     * @return True if the store exists, false otherwise.
     */
    public boolean checkStore(String nameStore){

        int i = 0;
        ArrayList<Store> stores = shopJSONDAO.readStores();
        while (i < stores.size()){
            if(stores.get(i).getName().equals(nameStore)){
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Creates a new store and adds it to the data store.
     *
     * @param nameStore       The name of the new store.
     * @param descriptionShop The description of the new store.
     * @param foundingYear    The founding year of the new store.
     * @param category        The category of the new store.
     */
    public void newStore(String nameStore, String descriptionShop, int foundingYear, String category) throws StoreNameAlreadyExists {
        Store store = new Store(nameStore, descriptionShop, foundingYear, 0, category, new ArrayList<Products>());
        int i = 0;

        // Check if allStores() is not null before calling isEmpty()
        ArrayList<Store> stores = allStores();
        if (stores != null && !stores.isEmpty()) {
            while (i < stores.size()) {
                if (nameStore.equals(stores.get(i).getName())) {
                    throw new StoreNameAlreadyExists();
                }
                i++;
            }
        }

        // Add the store outside the conditional check
        shopJSONDAO.addStore(store);
    }


    /**
     * Finds a store by its name.
     *
     * @param nameStore The name of the store to find.
     * @return The store with the specified name, or null if not found.
     * @throws StoreNameDoesNotExist The store name does not exist.
     */
    public Store findStoreByName(String nameStore) throws StoreNameDoesNotExist {
        if(shopJSONDAO.readStores().isEmpty()){
            return null;
        }
        int i = 0;
        ArrayList<Store> stores = shopJSONDAO.readStores();
        while (i < stores.size()){
            if(stores.get(i).getName().equals(nameStore)){
                return stores.get(i);
            }
            i++;
        }

        if(i == stores.size()){
            throw new StoreNameDoesNotExist();
        }

        return null;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return The list of all stores.
     */
    public ArrayList<Store> allStores(){
        ArrayList<Store> stores = shopJSONDAO.readStores();
        return stores;
    }

    /**
     * Removes a product from a store's catalog.
     *
     * @param nombre          The name of the store from which to remove the product.
     * @param posicionProduct The position of the product to remove.
     */
    public void removeProductFromStore(String nombre, int posicionProduct){
        shopJSONDAO.removeProductFromStore(nombre, posicionProduct);
    }

    /**
     * Removes a product from all stores' catalogs and updates the product's IDs.
     *
     * @param idProduct The ID of the product to remove.
     * @param products  The list of products to update IDs.
     */
    public void removeProductFromShop(int idProduct, ArrayList<Products> products) {
        ArrayList<Store> stores = allStores();

        for (int i = 0; stores.size() > i; i++) {
            for (int j = 0; stores.get(i).getCatalogue().size() > j; j++) {
                if (stores.get(i).getCatalogue().get(j).getId() == idProduct) {
                    shopJSONDAO.removeProductFromStore(stores.get(i).getName(), idProduct);
                }
            }
        }
        ArrayList<Store> storesFinal = allStores();

        for (int k = 0; products.size() > k; k++) {
            for (int h = 0; storesFinal.size() > h; h++) {
                for (int t = 0; storesFinal.get(h).getCatalogue().size() > t; t++) {
                    if (storesFinal.get(h).getCatalogue().get(t).getName().equals(products.get(k).getName())) {
                        storesFinal.get(h).getCatalogue().get(t).setId(products.get(k).getId());
                        shopJSONDAO.writeStores(storesFinal);
                    }
                }
            }
        }
        }

    /**
     * Retrieves the catalog of a specific store.
     *
     * @param indexStore The index of the store in the list of all stores.
     * @return The catalog of the specified store.
     * @throws NoProductsInCatalogue error no product in the catalogue.
     */
    public ArrayList<Products> listCatalogueStore (int indexStore) throws NoProductsInCatalogue {
            ArrayList<Store> stores = allStores();
            ArrayList<Products> products;
            products = (stores.get(indexStore - 1).getCatalogue());
            if(stores.get(indexStore - 1).getCatalogue().isEmpty()){
                throw new NoProductsInCatalogue();
            }
            return products;
    }

    /**
     * Checks if a product is present in a store's catalogue.
     *
     * @param idProduct The ID of the product to be checked.
     * @param catalogue The list of products representing the store's catalogue.
     * @return true if the product is present in the catalogue, false otherwise.
     */
    public boolean checkProductInCatalogue(int idProduct, ArrayList<Products> catalogue){
        int i = 0;
        while(catalogue.size() > i){

            if(idProduct == catalogue.get(i).getId()){
                return true;
            }
            i++;
        }
        return false;
    }



}