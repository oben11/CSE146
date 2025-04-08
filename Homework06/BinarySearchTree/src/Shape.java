// Oliver Benjamin
// CSE146
// Homework06

abstract class Shape implements Comparable<Shape> {

    public abstract double getArea();
    public abstract ShapeType getType();
    public abstract String getFileString(); // For writing to file

    @Override
    public int compareTo(Shape other) {
//Compares shapes first by area, then by type for BST ordering.
        if (this.getArea() < other.getArea()) {
            return -1;
        } else if (this.getArea() > other.getArea()) {
            return 1;
        } else {

            return this.getType().compareTo(other.getType());
        }
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}