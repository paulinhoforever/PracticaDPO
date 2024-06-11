package Presentation;

import Business.Entities.Products;
import Business.Entities.Store;
import Business.Managers.CartManager;
import Business.Managers.ProductsManager;
import Business.Managers.StoreManager;
import Exceptions.*;

import java.util.ArrayList;

/**
 * The Controller class acts as an intermediary between the UI and the business logic.
 */
public class Controller {
    private final ManagerUI ui;
    private final ProductsManager productsManager;
    private final StoreManager storeManager;
    private final CartManager cartManager;

    /**
     * Constructs a new Controller instance.
     *
     * @param ui             The user interface manager.
     * @param productsManager The products manager.
     * @param storeManager    The store manager.
     * @param cartManager     The cart manager.
     */
    public Controller(ManagerUI ui, ProductsManager productsManager, StoreManager storeManager, CartManager cartManager) {
        this.ui = ui;
        this.productsManager = productsManager;
        this.storeManager = storeManager;
        this.cartManager = cartManager;
    }

    /**
     * Runs the main loop of the controller, processing user input and interacting with the business logic.
     */
    public void run(){
        ArrayList<Store> purchasedShops = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {
            switch (ui.showMainMenu()) {
                case MANAGE_PRODUCTS:
                    boolean running = true;
                    while (running) {
                        switch (ui.ShowManageProductsMenu()) {
                            case CREATE_PRODUCT:
                                String productName = ui.requestProductsName();
                                if (!productsManager.comprovarProductExiste(productName)) {
                                    ui.showErrorMessage();
                                    running = false;
                                    break;
                                }
                                String productBrand = productsManager.comprovarMarcaProdcuto(ui.requestProductsBrand());
                                String typeTax = ui.showMenuProductCreation();
                                Float price = ui.requestMVP();
                                productsManager.newProduct(productName, productBrand, typeTax, price);
                                running = false;
                                ui.cleanBuffer();
                                break;
                            case REMOVE_PRODUCT:
                                ui.showMessageProducts();
                                try {
                                    ui.showListProducts(productsManager.listAllProducts());
                                } catch (NoProductsInCatalogue e) {
                                    throw new RuntimeException(e);
                                }
                                int indexProduct = ui.askForInteger(0, productsManager.listAllProducts().size() + 1);
                                if(indexProduct == productsManager.listAllProducts().size() + 1){
                                    break;
                                }
                                if(ui.askForValidation(productsManager.listAllProducts().get(indexProduct - 1).getName(), productsManager.listAllProducts().get(indexProduct - 1).getBrand())){
                                    int idproducto = productsManager.idProduct(indexProduct, productsManager.listAllProducts());
                                    productsManager.removeProduct(idproducto, indexProduct);
                                    storeManager.removeProductFromShop(idproducto, productsManager.listAllProducts());
                                }else {
                                    break;
                                }

                                break;
                            case BACK:
                                running = false;
                                break;
                        }
                    }
                    break;
                case MANAGE_SHOPS:
                    boolean runing = true;
                    while (runing) {
                        switch (ui.showManageShopsMenu()) {
                            case CREATE_SHOP:
                                String nameShop = ui.askForShopsName();
                                String descriptionShop = ui.askForShopDescription();
                                int fundatioYear = ui.askForFundationYear();
                                String categorie = ui.showMenuShopBusinessModels();
                                try {
                                    storeManager.newStore(nameShop, descriptionShop, fundatioYear, categorie);
                                    runing = false;
                                    break;
                                } catch (StoreNameAlreadyExists ex) {
                                    System.out.println(ex.getMessage());
                                    runing = false;
                                    break;
                                }

                            case EXPAND_SHOP_CATALOGUE:
                                nameShop = ui.askForShopsName();
                                if(!storeManager.checkStore(nameShop)){
                                    ui.showMessageErrorShopName();
                                    break;
                                }
                                String productsName = ui.requestProductsName();
                                if(productsManager.comprovarProductExiste(productsName)){
                                    ui.showMessageErrorProductName();
                                    break;
                                } else {
                                    try {
                                        if(storeManager.checkProductInCatalogue(productsManager.buscarProducto(productsName).getId(), storeManager.findStoreByName(nameShop).getCatalogue())){
                                            ui.showMessageErrorProductIsAlreadyInTheCatalogue();
                                            break;
                                        }
                                    } catch (StoreNameDoesNotExist e) {
                                        ui.showMessageException(e.getMessage());
                                        break;
                                    }
                                }
                                float priceProduct = ui.askForPriceProduct();
                                    if (storeManager.addProductToStore(nameShop, productsManager.añadirPrecioProductCatalogo(productsManager.buscarProducto(productsName), priceProduct))) {
                                        ui.showProductAddToCatalog(productsName, productsManager.buscarProducto(productsName).getBrand(), nameShop);
                                    } else {
                                        ui.showMessageErrorMRP();
                                    }
                                    break;


                            case REDUCE_SHOP_CATALOGUE:
                                try {
                                    nameShop = ui.askForShopsName();
                                    storeManager.findStoreByName(nameShop);
                                    ui.showListProducts(storeManager.showProductsOfStore(storeManager.findStoreByName(nameShop)));
                                    int positionProduct = ui.askForInt(1, storeManager.findStoreByName(nameShop).getCatalogue().size() + 1);
                                    if(positionProduct == storeManager.findStoreByName(nameShop).getCatalogue().size() + 1){
                                        break;
                                    }
                                    storeManager.removeProductFromStore(nameShop, productsManager.idProduct(positionProduct, (storeManager.showProductsOfStore(storeManager.findStoreByName(nameShop)))));
                                    break;
                                } catch (StoreNameDoesNotExist | NoProductsInCatalogue ex) {
                                    ui.showMessageException(ex.getMessage());
                                    runing = false;
                                    break;
                                }
                            case BACK:
                                runing = false;
                                break;
                        }
                    }
                    break;

                case SEARCH_PRODUCTS:
                    String query = ui.askForQuery();
                    try {
                        ArrayList<Products> productosEncontrados = productsManager.buscarProductosQuery(query);
                        int index = ui.showListQuery(storeManager.allStores(), productosEncontrados);
                        if(ui.askForIntegers(1, index + 1) == index + 1){
                            break;
                        }
                        switch (ui.menuReviewShowList()) {
                            case READ_REVIEWS ->
                                    ui.showReviews(productsManager.readReviewsOfProduct(productosEncontrados, index), productosEncontrados.get(index - 1));
                            case REVIEW_PRODUCT ->
                                    productsManager.addReviewToProduct(productosEncontrados, index, ui.askForReview(productosEncontrados.get(index - 1)));
                        }
                    } catch (NoProductsWithTheQueryYouProvide | NoReviewsInProduct ex) {
                        ui.showMessageException(ex.getMessage());
                    }
                    break;

                case LIST_SHOPS:

                    ui.showListShopMessage();
                    ui.showListStores(storeManager.allStores());
                    ArrayList<Store> stores = new ArrayList<>();
                    int index = ui.askForIntegers(1, storeManager.allStores().size() + 1);
                    if (index == (storeManager.allStores().size() + 1)) {
                        break;
                    } else {
                    stores.add(storeManager.allStores().get(index - 1));
                    try {
                        int subindex = ui.askForIntegers(1, ui.showCatalogueStore(storeManager.listCatalogueStore(index), storeManager.allStores(), index) + 1);
                        switch (ui.menuReviewCart()) {
                            case READ_REVIEWS:
                                ui.showReviews(productsManager.buscarProducto(storeManager.allStores().get(index - 1).getCatalogue().get(subindex - 1).getName()).getReviews(), storeManager.allStores().get(index - 1).getCatalogue().get(subindex - 1));
                            case REVIEW_PRODCUCT:
                                productsManager.addReviewToProduct(storeManager.listCatalogueStore(index), subindex, ui.askForReview(storeManager.allStores().get(index - 1).getCatalogue().get(subindex - 1)));
                                break;
                            case ADD_TO_CART:
                                try {
                                    cartManager.addToCart(storeManager.listCatalogueStore(index).get(subindex - 1));
                                    purchasedShops.add(storeManager.allStores().get(index - 1));
                                    ui.showProductAddedToCart(storeManager.listCatalogueStore(index).get(subindex - 1));
                                } catch (NoProductsInCatalogue ex) {
                                    ui.showMessageException(ex.getMessage());
                                }
                                break;
                        }
                    } catch (NoProductsInCatalogue | NoReviewsInProduct ex) {
                        ui.showMessageException(ex.getMessage());
                    }

                    break;
            }
                case YOUR_CART:
                        try {
                            ui.showListProductsCart(cartManager.listProductsCart(), cartManager.costCart(cartManager.listProductsCart()));
                            boolean run = true;
                            while (run) {

                                switch (ui.showMenuCart()) {
                                    case CHECKOUT:
                                        if(ui.askForCheckout()){
                                            ui.showCheckOut(cartManager.listProductsCart(), purchasedShops);
                                            run = false;
                                        }
                                        break;
                                    case CLEAR_CART:
                                        if(ui.askForClearCart()){
                                            cartManager.eraseCart(cartManager.listProductsCart());
                                            run = false;
                                            break;
                                        }else{
                                            ui.showMessageNoClearCart();
                                        }
                                        break;
                                    case BACK:
                                        run = false;
                                        break;
                                }
                            }
                        }catch (CartIsEmpty ex){
                            ui.showMessageException(ex.getMessage());
                        }
                        break;

                case EXIT:
                    isRunning = false;
                    break;
            }

        }
    }
}

