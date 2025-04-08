// Oliver Benjamin
// CSE146
// Homework06

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
             throw new IllegalArgumentException("Radius must be positive.");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }

    @Override
    public String toString() {
        return "Circle Radius: " + radius + " Area: " + getArea();
    }

    @Override
    public String getFileString() {
        return "Circle\t" + radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Circle)) return false;
        Circle circle = (Circle) obj;
        // Direct comparison 
        return this.radius == circle.radius;

    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}