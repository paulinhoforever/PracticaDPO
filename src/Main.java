import Business.Entities.Cart;
import Business.Entities.Products;
import Business.Entities.Store;
import Business.Managers.CartManager;
import Business.Managers.ProductsManager;
import Business.Managers.StoreManager;
import Exceptions.StoreNameDoesNotExist;
import Persistence.*;
import Presentation.Controller;
import Presentation.ManagerUI;

/**
 * The main entry point of the application.
 * This class initializes the necessary components and starts the application.
 */
public class Main {

        /**
         * The main method where the application starts.
         * It initializes the managers, entities, and the controller,
         * then starts the application by calling the run method on the controller.
         *
         * @param args Command-line arguments (not used in this application).
         */
    public static void main(String[] args){

            ShopJSONDAO shopJSONDAO = new ShopJSONDAO();
            productsJSONDAO ProductsJSONDAO = new productsJSONDAO();
            Products products = new Products();
            Store stores = new Store();
            Cart cart = new Cart();
            CartManager cartManager = new CartManager(cart);
            ManagerUI managerUI = new ManagerUI();
            StoreManager storeManager = new StoreManager(shopJSONDAO, products, stores);
            ProductsManager productsManager = new ProductsManager(ProductsJSONDAO, products);
            Controller controller = new Controller(managerUI, productsManager, storeManager, cartManager);

            controller.run();
    }
}