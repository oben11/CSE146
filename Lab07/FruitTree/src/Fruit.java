// Oliver Benjamin
// CSE146
// Lab07

import java.util.Arrays;
import java.util.List;

public class Fruit implements Comparable<Fruit> {

    // Instance vars
    private String type;
    private double weight;

    // fruit types
    private static final List<String> VALID_TYPES = Arrays.asList("apple", "orange", "banana", "kiwi", "tomato");
    // Default
    private static final String DEFAULT_TYPE = "apple";
    private static final double DEFAULT_WEIGHT = 1.0;

    public Fruit() {
        this.type = DEFAULT_TYPE;
        this.weight = DEFAULT_WEIGHT;
    }

    public Fruit(String type, double weight) {
        setType(type); 
        setWeight(weight);
    }


    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }


    public void setType(String type) {
        if (type != null && VALID_TYPES.contains(type.toLowerCase())) {
            this.type = type;
        } else {
            this.type = DEFAULT_TYPE;
        }
    }

    public void setWeight(double weight) {
        if (weight > 0.0) {
        this.weight = weight;
        } else {
        this.weight = DEFAULT_WEIGHT;
        }
    }


    // toString Method
    @Override
    public String toString() {
        return String.format("Type: %s Weight: %.17g", type, weight); 
    }

    @Override
    public int compareTo(Fruit other) {
        if (other == null) {
            return -1; 
        }

        int weightComparison = Double.compare(this.weight, other.weight);

        if (weightComparison != 0) {
            return weightComparison;
        } else {
            // Weights are equal, compare by type (case-sensitive as per String.compareTo)
            return this.type.compareTo(other.type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        // Use Double.compare for weight check
        return Double.compare(fruit.weight, weight) == 0 &&
               java.util.Objects.equals(type, fruit.type);
    }

    @Override
    public int hashCode() {
    return java.util.Objects.hash(type, weight);
    }
}