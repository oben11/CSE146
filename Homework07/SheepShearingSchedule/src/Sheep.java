// Oliver Benjamin
// CSE146
// Homework07

public class Sheep implements Comparable<Sheep> {
    private String name;
    private int shearTime;
    private int arrivalTime;

    // Default
    public Sheep() {
        this.name = "";
        this.shearTime = 0;
        this.arrivalTime = 0;
    }

    // Constructor with init values
    public Sheep(String name, int shearTime, int arrivalTime) {
        this.name = name;
        this.shearTime = shearTime;
        this.arrivalTime = arrivalTime;
    }

    // Accessors 

    // Gets the name of the sheep.

    public String getName() {
        return name;
    }

    // Gets the shear time of the sheep.

    public int getShearTime() {
        return shearTime;
    }

    // Gets the arrival time of the sheep.

    public int getArrivalTime() {
        return arrivalTime;
    }

    // Mutators (Setters)

     // Sets the name of the sheep.

    public void setName(String name) {
        this.name = name;
    }

    // Sets the shear time of the sheep.

    public void setShearTime(int shearTime) {
        this.shearTime = shearTime;
    }


    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    // Other useful methods
    @Override
    public String toString() {
        return "Sheep{" +
               "name='" + name + '\'' +
               ", shearTime=" + shearTime +
               ", arrivalTime=" + arrivalTime +
               '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sheep sheep = (Sheep) obj;
        return shearTime == sheep.shearTime && arrivalTime == sheep.arrivalTime && java.util.Objects.equals(name, sheep.name);
    }

    @Override
    public int compareTo(Sheep otherSheep) {
    //arrivalTime (earliest arrival comes first)
        int arrivalComparison = Integer.compare(this.arrivalTime, otherSheep.arrivalTime);
        if (arrivalComparison != 0) {
            return arrivalComparison;
        }
    
        // shearTime (shortest shear time comes first)
        int shearComparison = Integer.compare(this.shearTime, otherSheep.shearTime);
        if (shearComparison != 0) {
            return shearComparison;
        }
    
        // name compare
        return this.name.compareTo(otherSheep.name);
    }
}