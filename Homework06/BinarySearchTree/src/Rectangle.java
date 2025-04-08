// Oliver Benjamin
// CSE146
// Homework06

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Length and width must be positive.");
        }
        // Keep consistent storage for easier comparison later if needed
        this.length = length;
        this.width = width;
    }

    public double getLength() { return length; }
    public double getWidth() { return width; }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }

    @Override
    public String toString() {
        // Using the stored fields directly
         return "Rectangle Length: " + this.length + " Width: " + this.width + " Area: " + getArea();
    }

     @Override
    public String getFileString() {
        return "Rectangle\t" + this.length + "\t" + this.width;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rectangle)) return false; 
        Rectangle rectangle = (Rectangle) obj;
        // Check if dimensions match, allowing for swapped length or widt
        return (this.length == rectangle.length && this.width == rectangle.width) ||
               (this.length == rectangle.width && this.width == rectangle.length);

    }

    @Override
    public int hashCode() {
        // Hash code should be order-independent if equals is
        return Double.hashCode(length) + Double.hashCode(width);

    }
}