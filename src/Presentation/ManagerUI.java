package Presentation;

import Business.Entities.Products;
import Business.Entities.Store;
import Exceptions.CartIsEmpty;
import Exceptions.NoProductsInCatalogue;
import Exceptions.NoReviewsInProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the user interface for managing various tasks such as managing products,
 * managing shops, reviewing products, handling the shopping cart, and more.
 * It provides methods to display menus, prompt user input, and handle exceptions related to user interactions.
 */
public class ManagerUI {
    private final Scanner scanner;
    /**
     * Constructs a new {@code ManagerUI} object.
     * Initializes a {@code Scanner} instance for reading input from the console.
     * This constructor sets up the necessary components for managing user interactions.
     */
    public ManagerUI() {
        scanner = new Scanner(System.in);
    }
    private static final String AVERAGE_RATING = "Average rating: ";
    private static final String ERROR_WRONG_OPTION = "Error, the entered option is not a valid option.";
    private static final String PRODUCT_CATEGORIES = "The system supports the following product categories: ";
    private static final String PRODUCT_MAX_VALUABLE_PRICE = "Introduce the maximum valuable price: ";
    private static final String HEADER_CART = "Your cart contains the following items: ";
    private static final String CHECKOUT_SURE = "Are you sure you want to checkout? ";
    private static final String CLEAR_CART_SURE = "Are you sure you want to clear your cart? ";
    private static final String MESSAGE_LIST_PRODUCTS = "These are the currently available products:";
    private static final String MESSAGE_LIST_PRODUCTS_REMOVE = "Which one would you like to remove? ";
    private static final String SHOP_PRODUCTS_MESSAGE = "This shop sells the following products: ";
    private static final String ENTER_QUERY = "Enter your query: ";
    private static final String ERROR_MRP = "Error the price is over the maximum retail price";
    private static final String WHICH_CATALOG_WANT = "Which catalogue do you want to see? ";
    private static final String WHICH_INTERESTED = "Which one are you interested in? ";
    private static final String PRODUCT_ALREADY_EXISTS = "ERROR, the product already exists.";
    private static final String REVIEW = "Please rate the product (1-5 stars): ";
    private static final String REVIEW_COMMENT = "Please add a comment to your review: ";
    private static final String SURE_CLEAR_CART = "Are you sure you want to clear your cart? ";
    private static final String NO_CLEAR_CART = "Your car has not been cleared";
    private static final String ERROR_INTEGER = "Error: Please enter a positive integer.\nTry again: ";
    private static final String ERROR_FLOAT = "Error: Please enter a positive float.\nTry again: ";
    private static final String SHOP_ERROR = "Error: The shop do not exist.";
    private static final String ERROR_PRODUCT_NAME = "Error: The product does not exist.";
    private static final String ERROR_PRODUCT_IS_IN_CATALOGUE = "Error: The product is in the catalogue already";
    private static final String MAIN_MENU_MESSAGE =
            """
                    1) Manage Products
                    2) Manage Shops
                    3) Search Products
                    4) List shops
                    5) Your cart

                    6) Exit
                    Choose a Digital Shopping Experience:\s""";

    /**
     * A string representing the menu options for managing reviews and adding products to the cart.
     * This menu provides options for reading reviews, reviewing a product, and adding a product to the cart.
     * It prompts the user to choose an option from the listed actions.
     */
    public static final String MENU_REVIEWS_CART =
            """
                       1) Read Reviews
                       2) Review Product
                       3) Add to Cart

                    Choose an option:\s""";

    /**
     * A string representing the menu options for reviewing products.
     * This menu provides options for reading reviews and reviewing a product.
     * It prompts the user to choose an option.
     */
    public static final String MENU_REVIEW_SHOW_LIST =
            """
                       1) Read Reviews
                       2) Review Product

                    Choose an option:\s""";

    /**
     * A string representing the shopping cart menu options.
     * This menu provides options for checking out, clearing the cart, and going back.
     * It prompts the user to choose an option.
     */
    public static final String CART_MENU =
            """
                       1) Checkout
                       2) Clear cart

                       3) Back

                    Choose an option:\s""";

    /**
     * A string representing the menu options for managing products.
     * This menu provides options for creating and removing products, and going back.
     * It prompts the user to choose an option.
     */
    private static final String MENU_MANAGE_PRODUCTS =
            """
                    1) Create a Product
                    2) Remove a Product

                    3) Back

                    Choose an option:\s""";

    /**
     * A string representing the menu options for managing shops.
     * This menu provides options for creating a shop, expanding a shop's catalogue,
     * reducing a shop’s catalogue, and going back. It prompts the user to choose an option.
     */
    private static final String MENU_MANAGE_SHOPS =
            """
                    1) Create a Shop
                    2) Expand a Shop's Catalogue
                    3) Reduce a Shop’s Catalogue

                    4) Back

                    Choose an option:\s""";

    /**
     * A string representing the menu options for selecting product categories.
     * This menu provides options for general, reduced taxes, and super-reduced taxes categories.
     * It prompts the user to pick the product's category.
     */
    private static final String MENU_CATEGORIES =
            """
                    A) General
                    B) Reduced Taxes
                    C) Superreduced Taxes
                    Please pick the product's category:\s""";

    /**
     * A string representing the menu options for selecting a shop's business model.
     * This menu provides options for maximum benefits, loyalty, and sponsored models.
     * It prompts the user to pick the shop’s business model.
     */
    private static final String MENU_BUSSINESS_MODELS =
            """
                    A) Maximum Benefits\s
                    B) Loyalty
                    C) Sponsored

                    Please pick the shop’s business model:\s""";
    private static final String PRODUCTS_NAME = "Please enter the product’s name: ";
    private static final String PRODCUTS_BRAND = "Please enter the product’s brand: ";
    private static final String SHOPS_NAME = "Please enter the shop’s name: ";
    private static final String SHOPS_MENU_HEADER = "The system supports the following business models: ";
    private static final String SHOPS_DESCRIPTION = "Please enter the shop’s description: ";
    private static final String SHOPS_FUNDATION_YEAR = "Please enter the shop’s founding year: ";
    private static final String PRODUCT_PRICE_SHOP = "Please enter the product’s price at this shop: ";
    private static final String LIST_SHOPS_EL_COFRE = "The elCofre family is formed by the following shops: \n\nThe following products where found: ";
    private static final String NO_PRODUCT_BEING_SOLD = "This product is not currently being sold in any shops.";
    //private static final String WHICH_ONE_YOU_WANT = "Which one are you interested in? ";
    private static final String ERROR_ENTERING_REVIEW = "You introduced something unexpected. Remember the only thing the program accept is the \"*\". Maximum 5 and minimum 1";

    /**
     * Shows the main menu and handles user input to determine the next action.
     *
     * @return The selected main menu option.
     */
    public MainMenuOptions showMainMenu() {
        do {
            System.out.println();
            System.out.print(MAIN_MENU_MESSAGE);

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return MainMenuOptions.MANAGE_PRODUCTS;
                    case 2:
                        return MainMenuOptions.MANAGE_SHOPS;
                    case 3:
                        return MainMenuOptions.SEARCH_PRODUCTS;
                    case 4:
                        return MainMenuOptions.LIST_SHOPS;
                    case 5:
                        return MainMenuOptions.YOUR_CART;
                    case 6:
                        return MainMenuOptions.EXIT;
                    default:
                        System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (true);
    }

    /**
     * Shows the menu manage products and handles user input to determine the next action.
     *
     * @return The selected  menu manage products.
     */
    public MenuOptionsManageProducts ShowManageProductsMenu() {
        do {
            System.out.println();
            System.out.print(MENU_MANAGE_PRODUCTS);

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return MenuOptionsManageProducts.CREATE_PRODUCT;
                    case 2:
                        return MenuOptionsManageProducts.REMOVE_PRODUCT;
                    case 3:
                        return MenuOptionsManageProducts.BACK;
                    default:
                        System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (true);
    }

    /**
     * Shows the menu reviews cart and handles user input to determine the next action.
     *
     * @return The selected  menu reviews cart.
     */
    public MenuReviewCart menuReviewCart(){
        do {
            System.out.println();
            System.out.print(MENU_REVIEWS_CART);

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return MenuReviewCart.READ_REVIEWS;
                    case 2:
                        return MenuReviewCart.REVIEW_PRODCUCT;
                    case 3:
                        return MenuReviewCart.ADD_TO_CART;
                    default:
                        System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (true);

    }

    /**
     * Displays the menu for reviewing products and returns the user's choice.
     * The menu provides options for reading reviews or reviewing a product.
     * It continues to prompt the user until a valid option is selected.
     *
     * @return A {@code MenuReviewShowList} enum value representing the user's choice.
     *         {@code MenuReviewShowList.READ_REVIEWS} if the user chooses to read reviews,
     *         {@code MenuReviewShowList.REVIEW_PRODCUCT} if the user chooses to review a product.
     */
    public MenuReviewShowList menuReviewShowList(){
        do {
            System.out.println();
            System.out.print(MENU_REVIEW_SHOW_LIST);

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return MenuReviewShowList.READ_REVIEWS;
                    case 2:
                        return MenuReviewShowList.REVIEW_PRODUCT;
                    default:
                        System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (true);

    }

    /**
     * Shows the cart menu and handles user input to determine the next action.
     *
     * @return The selected cart menu options.
     */
    public MenuCart showMenuCart(){
        do {
            System.out.println();
            System.out.print(CART_MENU);

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return MenuCart.CHECKOUT;
                    case 2:
                        return MenuCart.CLEAR_CART;
                    case 3:
                        return MenuCart.BACK;
                    default:
                        System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (true);
    }

    /**
     * Shows the menu manage shops and handles user input to determine the next action.
     *
     * @return The selected  menu manage shops options.
     */
    public MenuProductCreation showManageShopsMenu() {
        do {
            System.out.println();
            System.out.print(MENU_MANAGE_SHOPS);

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return MenuProductCreation.CREATE_SHOP;
                    case 2:
                        return MenuProductCreation.EXPAND_SHOP_CATALOGUE;
                    case 3:
                        return MenuProductCreation.REDUCE_SHOP_CATALOGUE;
                    case 4:
                        return MenuProductCreation.BACK;
                    default:
                        System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (true);
    }

    /**
     * Shows the menu categories and handles user input to determine the next action.
     *
     * @return The selected  menu categories.
     */
    public String showMenuProductCreation() {
        String selectedCategory = null;

        do {
            System.out.println(PRODUCT_CATEGORIES);
            System.out.print(MENU_CATEGORIES);

            try {
                char option = scanner.nextLine().toUpperCase().charAt(0);
                switch (option) {
                    case 'A' -> selectedCategory = "GENERAL";
                    case 'B' -> selectedCategory = "REDUCED TAXES";
                    case 'C' -> selectedCategory = "SUPERREDUCED TAXES";
                    default -> System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (Exception e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (selectedCategory == null);

        return selectedCategory;
    }

    /**
     * Shows the shops menu header and handles user input to determine the next action.
     *
     * @return The selected shop menu header.
     */
    public String showMenuShopBusinessModels() {
        String selectedCategory = null;

        do {
            System.out.println(SHOPS_MENU_HEADER);
            System.out.print(MENU_BUSSINESS_MODELS);

            try {
                char option = scanner.nextLine().toUpperCase().charAt(0);
                switch (option) {
                    case 'A' -> selectedCategory = "Maximum Benefits";
                    case 'B' -> selectedCategory = "Loyalty";
                    case 'C' -> selectedCategory = "Sponsored";
                    default -> System.out.println(ERROR_WRONG_OPTION);
                }
            } catch (Exception e) {
                System.out.println(ERROR_WRONG_OPTION);
            }
        } while (selectedCategory == null);

        return selectedCategory;
    }

    /**
     * Requests and validates the user input for the maximum valuable price (MVP) of a product.
     * Continues to prompt the user until a valid positive float value is entered.
     *
     * @return The validated maximum valuable price (MVP) entered by the user.
     */
    public float requestMVP() {
        float price = 0;
        boolean validInput = false;

        do {
            System.out.print(PRODUCT_MAX_VALUABLE_PRICE);
            if (scanner.hasNextFloat()) {
                price = scanner.nextFloat();
                if (price > 0) {
                    validInput = true;
                } else {
                    System.out.println(ERROR_WRONG_OPTION);
                }
            } else {
                System.out.println(ERROR_WRONG_OPTION);
                scanner.next();
            }
        } while (!validInput);

        return price;
    }

    /**
     * Requests and retrieves the user input for the name of a product.
     *
     * @return The name of the product entered by the user.
     */
    public String requestProductsName() {
        System.out.print(PRODUCTS_NAME);
        return scanner.nextLine();
    }

    /**
     * Requests and retrieves the user input for the brand of a product.
     *
     * @return The brand of the product entered by the user.
     */
    public String requestProductsBrand() {
        System.out.print(PRODCUTS_BRAND);
        return scanner.nextLine();
    }

    /**
     * Displays a list of products, each with its name and brand. If there are no products in the list,
     * it throws a {@link NoProductsInCatalogue} exception.
     *
     * @param products An {@code ArrayList} of {@code Products} to be displayed.
     * @throws NoProductsInCatalogue If the list of products is empty.
     */
    public void showListProducts(ArrayList<Products> products) throws NoProductsInCatalogue {
        int i = 1;

        if(products.size() == 0){
            throw new NoProductsInCatalogue();
        }
        showMessageProductsShops();
        while (i - 1 < products.size()) {
            System.out.println("    " + i + ")" + "\"" + products.get(i - 1).getName() + "\"" + " by " + "\"" + products.get(i - 1).getBrand() + "\"");
            i++;
        }
        System.out.println("\n    " + i + ")" + " Back\n");
    }

    /**
     * Displays a list of products, including their names and brands, to the user.
     *
     * @param shops List of shops that will be shown.
     * @param products The list of products to be displayed.
     * @return i return the id of a shop.
     */
    public int showListQuery(ArrayList<Store> shops, ArrayList<Products> products) {
        int i = 0, j = 0, k = 0, d = 0;

        while (i < products.size()) {
            d = 0;
            System.out.println("\n" + (i+1) + ") " + products.get(i).getName() + " by " + products.get(i).getBrand());

            for (Store shop : shops) {
                for (Products product : shop.getCatalogue()) {
                    if (products.get(i).getName().equals(product.getName())) {
                        if (d == 0) {
                            System.out.println("Sold at: ");
                        }
                        System.out.println("    - " + shop.getName() + ": " + product.getPrice());
                        d++;
                        break;
                    }
                }
            }

            if (d == 0) {
                System.out.println("This product is not currently being sold in any shops.");
            }

            i++;
        }
        System.out.print("\n" + (i+1) + ")" + " Back\n\nWhich one would you like to review? ");
        return i;
    }


    /**
     * Asks the user to input an integer within the specified range.
     *
     * @param min The minimum value allowed.
     * @param max The maximum value allowed.
     * @return The integer input by the user within the specified range.
     */
    public int askForInteger(int min, int max) {
        System.out.print("Which one would you like to remove? ");
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine());

                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println(ERROR_WRONG_OPTION);
                    System.out.print("Try again: ");
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_WRONG_OPTION);
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Prompts the user to input an integer and returns the entered value.
     *
     * @param min Min option possible
     * @param max Max option possible
     * @return The integer input by the user.
     */
    public int askForInt(int min, int max) {
        System.out.print(MESSAGE_LIST_PRODUCTS_REMOVE);
        while (true) {
            int entrada = Integer.parseInt(scanner.nextLine());
            if (entrada >= min && entrada <= max) {
                return entrada;
            } else {
                System.out.println("Error: The number has to be between [" + min + ", " + max + "]");
            }
        }
    }

    /**
     * Prompts the user to enter an integer within a specified range.
     * Continues to prompt until a valid integer within the range is provided.
     *
     * @param min The minimum acceptable value (inclusive).
     * @param max The maximum acceptable value (inclusive).
     * @return An integer within the specified range.
     */
    public int askForIntegers(int min, int max) {
        while (true) {
            try {
                int entrada = Integer.parseInt(scanner.nextLine());
                if (entrada >= min && entrada <= max) {
                    return entrada;
                } else {
                    System.out.print("Error: The number has to be between [" + min + ", " + max + "]. Try again: ");
                }
            } catch (NumberFormatException e) {
                System.out.print(ERROR_INTEGER);
            }
        }
    }

    /**
     * Asks the user for validation to remove a product from sale, based on its name and brand.
     * This method prompts the user to confirm the removal by entering 'yes' or 'no'.
     * If the input is invalid, it will prompt again.
     *
     * @param name  The name of the product to be removed.
     * @param brand The brand of the product to be removed.
     * @return True if the user confirms the removal, false if the user declines.
     */
    public boolean askForValidation(String name, String brand) {
        System.out.print("Are you sure you want to remove \"" + name + "\" by " +"\"" + brand + "\"" +"?");
        String validate = scanner.nextLine().toLowerCase();

        if (validate.equals("yes")) {
            System.out.println("\"" + name + "\"" + "by" + "\"" + brand + "\"" + "has been withdraw from sale");
            return true;
        } else if (validate.equals("no")) {
            return false;
        } else {
            System.out.println("Invalid validate. Please enter 'yes' or 'no'.");
            return askForValidation(name, brand);
        }
    }
    

    /**
     * Prompts the user to input a shop's name and returns the entered value.
     *
     * @return The shop name input by the user.
     */
    public String askForShopsName() {
        System.out.print(SHOPS_NAME);
        return scanner.nextLine();
    }

    /**
     * Prompts the user to input a shop's description and returns the entered value.
     *
     * @return The shop description input by the user.
     */
    public String askForShopDescription() {
        System.out.print(SHOPS_DESCRIPTION);
        return scanner.nextLine();
    }

    /**
     * Asks the user for the foundation year of a shop.
     *
     * @return The foundation year input by the user.
     */
    public int askForFundationYear() {
        System.out.print(SHOPS_FUNDATION_YEAR);
        while (true) {
            try {
                int year = Integer.parseInt(scanner.nextLine());
                if (year > 0) {
                    return year;
                } else {
                    System.out.println(ERROR_INTEGER);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INTEGER);

            }
        }
    }

    /**
     * Clears the buffer by consuming the remaining newline character.
     */
    public void cleanBuffer() {
        scanner.nextLine();
    }

    /**
     * Prompts the user to input a product's price at a shop and returns the entered value.
     *
     * @return The product price input by the user.
     */
    public float askForPriceProduct() {
        while (true) {
            System.out.print(PRODUCT_PRICE_SHOP);
            try {
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(ERROR_FLOAT);
            }
        }
    }

    /**
     * Displays a message listing products.
     */
    public void showMessageProducts() {
        System.out.println(MESSAGE_LIST_PRODUCTS + "\n");
    }

    /**
     * Prompts the user to input a query and returns the entered value.
     *
     * @return The user-entered query.
     */
    public String askForQuery() {
        System.out.print(ENTER_QUERY);
        return scanner.nextLine();
    }

    /**
     * Displays a message about shop products.
     */
    public void showMessageProductsShops() {
        System.out.println(SHOP_PRODUCTS_MESSAGE);
    }

    /**
     * Displays an error message related to Maximum Retail Price (MRP).
     */
    public void showMessageErrorMRP() {
        System.out.println(ERROR_MRP);
    }

    /**
     * Displays an error message related to shop name.
     */
    public void showMessageErrorShopName() {
        System.out.println(SHOP_ERROR);
    }

    /**
     * Displays an error message related to product name.
     */
    public void showMessageErrorProductName() {
        System.out.println(ERROR_PRODUCT_NAME);
    }

    /**
     * Displays an error message related to products are already in the catalogue.
     */
    public void showMessageErrorProductIsAlreadyInTheCatalogue() {
        System.out.println(ERROR_PRODUCT_IS_IN_CATALOGUE);
    }

    /**
     * Displays a message indicating the addition of a product to a shop's catalog.
     *
     * @param nameProduct The name of the product.
     * @param nameBrand   The brand of the product.
     * @param nameShop    The name of the shop.
     */
    public void showProductAddToCatalog(String nameProduct, String nameBrand, String nameShop) {
        System.out.println("\"" + nameProduct + "\" by " + "\"" + nameBrand + "\"" + " is now being sold at " + "\"" + nameShop + "\".");
    }

    /**
     * Displays a message listing available shops.
     */
    public void showListShopMessage() {
        System.out.println();
        System.out.println(LIST_SHOPS_EL_COFRE);
    }

    /**
     * Displays a list of stores to the user.
     *
     * @param stores The list of stores to be displayed.
     */
    public void showListStores(ArrayList<Store> stores) {
        int i = 1;
        System.out.println();
        while (i - 1 < stores.size()) {
            System.out.println("    " + i + ") " + stores.get(i - 1).getName());
            i++;
        }
        System.out.println();
        System.out.print("    " + i + ") " + " Back\n\n" + WHICH_CATALOG_WANT);
    }


    /**
     * Displays the catalog of a store including product details.
     *
     * @param products The list of products in the store's catalog.
     * @param stores   The list of stores.
     * @param index    The index of the selected store in the list.
     * @return an id of a store.
     */
    public int showCatalogueStore(ArrayList<Products> products, ArrayList<Store> stores, int index) {
        int i;

        System.out.println("\n " + stores.get(index - 1).getName() + " - Since " + stores.get(index - 1).getYearOfFoundation());
        System.out.println(stores.get(index - 1).getDescription());
        System.out.println();
        for (i = 0; i < products.size(); i++) {
            System.out.println("    " + (i + 1) + ") \"" + products.get(i).getName() + "\" by \"" + products.get(i).getBrand() + "\"\n" + "     Price: " + products.get(i).getPrice() + "\n");
        }
        System.out.print("    " + (i + 1) + ") " + " Back\n\n" + WHICH_INTERESTED);
        return i;
    }

    /**
     * Prompts the user for a product review and returns the entered review.
     *
     * @param products The product for which the review is being submitted.
     * @return The user-entered product review.
     */
    public String askForReview(Products products){
        System.out.println(REVIEW);
        String asteriscos = scanner.nextLine();
        while (!asteriscos.equals("*") && !asteriscos.equals("**") && !asteriscos.equals("***") && !asteriscos.equals("****") && !asteriscos.equals("*****")){
            System.out.println(ERROR_ENTERING_REVIEW);
            asteriscos = scanner.nextLine();
        }
        System.out.print(REVIEW_COMMENT);
        String comment = scanner.nextLine();
        String review = switch (asteriscos) {
            case "*" -> "1* " + comment;
            case "**" -> "2* " + comment;
            case "***" -> "3* " + comment;
            case "****" -> "4* " + comment;
            default -> "5* " + comment;
        };

        System.out.println();
        System.out.println("Thank you for your review of \"" + products.getName() + "\" by \"" + products.getBrand() + "\".");

        return review;
    }

    /**
     * Displays a message indicating that a product has been added to the cart.
     *
     * @param product The product that has been added to the cart.
     */
    public void showProductAddedToCart(Products product){
        System.out.println("1x \"" + product.getName() + "\" by \"" + product.getBrand() + "\" has been added to your cart");
    }

    /**
     * Displays the list of products in the cart along with the total cost.
     *
     * @param products   The list of products in the cart.
     * @param totalCost  The total cost of all products in the cart.
     * @throws CartIsEmpty If the cart is empty shows an error.
     */
    public void showListProductsCart(ArrayList<Products> products, float totalCost) throws CartIsEmpty{

        if(products.size() != 0) {
            System.out.println();
            System.out.println(HEADER_CART);

            for (Products product : products) {
                System.out.println("    - \"" + product.getName() + "\" by \"" + product.getBrand() + "\"\n" + "    Price: " + product.getPrice());
                System.out.println();
            }
            System.out.println();
            System.out.println("Total: " + totalCost);
        } else {
            System.out.println();
            throw new CartIsEmpty();
        }
    }

    /**
     * Asks the user if they want to proceed with the checkout and returns the user's choice.
     * @return false.
     */
    public boolean askForCheckout(){

        System.out.print(CHECKOUT_SURE);
        String input = scanner.nextLine();

        do{
            if(input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("yes")){
                return true;
            }else if(input.equalsIgnoreCase("NO") || input.equalsIgnoreCase("no")){
                return false;
            }
        }while (input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("NO"));

        return false;
    }

    /**
     * Asks the user for confirmation to clear the cart and returns true if the user confirms, false otherwise.
     *
     * @return true if the user confirms to clear the cart, false otherwise.
     */
    public boolean askForClearCart(){

        System.out.print(SURE_CLEAR_CART);
        String input = scanner.nextLine();

        do{
            if(input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("yes")){
                return true;
            }else if(input.equalsIgnoreCase("NO") || input.equalsIgnoreCase("no")){
                return false;
            }
        }while (input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("NO"));

        return false;
    }

    /**
     * Calculates and displays the average review rating based on the provided list of reviews.
     *
     * @param reviews The list of reviews for which to calculate the average rating.
     */
    public void averageReviewRating(List<String> reviews){
        int i = 0;
        float numero2 = 0;

        while (i < reviews.size()){
            float numero = Float.parseFloat(reviews.get(i).substring(0 ,1));
            numero2 = numero + numero2;
            i++;
        }
        System.out.println("\n    " + AVERAGE_RATING + numero2/i + "*");
    }

    /**
     * Displays the reviews of a product along with the average review rating.
     *
     * @param reviews The list of reviews for the product.
     * @param product The product for which reviews are being displayed.
     * @throws NoReviewsInProduct If there are no reviews for the product.
     */
    public void showReviews(List<String> reviews, Products product) throws NoReviewsInProduct {
        if (reviews == null) {
            throw new NoReviewsInProduct();
        }

        int i = 0;

        System.out.println("\nThese are the reviews for \"" + product.getName() + "\" by \"" + product.getBrand() + "\"\n");
        while (i < reviews.size()) {
            System.out.println("    " + reviews.get(i));
            i++;
        }
        averageReviewRating(reviews);
    }

    /**
     * Displays an error message indicating that a product already exists.
     */
    public void showErrorMessage(){
        System.out.println("\n" + PRODUCT_ALREADY_EXISTS);
    }

    /**
     * Displays checkout information, updates store earnings, and returns the updated list of stores.
     *
     * @param products The list of products in the cart.
     * @param stores   The list of stores.
     * @return The updated list of stores with updated earnings.
     */
    public ArrayList<Store>  showCheckOut(ArrayList<Products> products, ArrayList<Store> stores){
        int i = 0;
        System.out.println(products.size() + "    " + stores.size());
        while(products.size() > i){
            System.out.println("\"" + stores.get(i).getName() + "\"" + " has earned " + products.get(i).getPrice() + ", for an historial total of " +  (stores.get(i).getEarnings() + products.get(i).getPrice()));
            stores.get(i).setEarnings((stores.get(i).getEarnings() + products.get(i).getPrice()));
            i++;
        }
        return stores;
    }

    /**
     * Displays a message indicating that the cart was not cleared.
     */
    public void showMessageNoClearCart(){
        System.out.println(NO_CLEAR_CART);
    }

    /**
     * Displays a message with the provided exception message.
     *
     * @param message The exception message to be displayed.
     */
    public void showMessageException(String message){
        System.out.println(message);
    }

}