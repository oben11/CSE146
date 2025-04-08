// Oliver Benjamin
// CSE146
// Homework06

class RightTriangle extends Shape {
    private double base;
    private double height;

    public RightTriangle(double base, double height) {
        if (base <= 0 || height <= 0) {
             throw new IllegalArgumentException("Base and height must be positive.");
        }
        this.base = base;
        this.height = height;
    }

    public double getBase() { return base; }
    public double getHeight() { return height; }


    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

     @Override
    public ShapeType getType() {
        return ShapeType.RIGHT_TRIANGLE;
    }

    @Override
    public String toString() {
        return "Right Triangle Base: " + base + " Height: " + height + " Area: " + getArea();
    }

    @Override
    public String getFileString() {
        return "Right Triangle\t" + base + "\t" + height;
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RightTriangle)) return false;
        RightTriangle that = (RightTriangle) obj;
        return this.base == that.base && this.height == that.height;

    }

    @Override
    public int hashCode() {
        // Simple hash combining fields
        int result = Double.hashCode(base);
        result = 31 * result + Double.hashCode(height); 
        return result;
    }
}