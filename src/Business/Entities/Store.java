package Business.Entities;

public class Store {

    private String name;
    private String description;
    private int year_of_foundation;
    private float earnings;
    private String category; // "GENERAL", "REDUCED", "SUPER_REDUCED"

    // Constructors, getters, setters, and other methods can be added here

    public Store(String name, String description, int year_of_foundation, float earnings, String category) {
        this.name = name;
        this.description = description;
        this.year_of_foundation = year_of_foundation;
        this.earnings = earnings;
        // Add validation for category to ensure it is one of the allowed values
        if (isValidCategory(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    private boolean isValidCategory(String category) {
        return category.equals("GENERAL") || category.equals("REDUCED") || category.equals("SUPER_REDUCED");
    }

    // Add other methods, getters, setters, etc.
}
