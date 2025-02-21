// Oliver Benjamin
// CSE 146
// Lab03

public class GroceryItem {
    // GroceryItem parameters
    private String name;
    private Double value;

    // constructors and default constructor
    public GroceryItem(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public GroceryItem() {
        this.name = "none";
        this.value = 0.0;
    }

    // accessors
    public String getName() {
        return this.name;
    }

    public Double getValue() {
        return this.value;
    }

    // override function that allows the object to be printed.
    @Override
    public String toString() {
        return String.format("[NAME] %s [VALUE] $%s", name , value);
    }
    
    
    
}
