// Oliver Benjamin
// CSE146
// Homework07

public class Order implements Comparable<Order> {
    private String customer;
    private String foodOrder;
    private int cookingTime;
    private int arrivalTime;
    private int cookingTimeLeft;

    public Order() {
        this.customer = "none";
        this.foodOrder = "none";
        this.cookingTime = 1;
        this.arrivalTime = 0;
        this.cookingTimeLeft = 1;
    }

    public Order(String customer, String foodOrder, int cookingTime, int arrivalTime) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer name cannot be null.");
        }
        if (foodOrder == null) {
            throw new IllegalArgumentException("Food order cannot be null.");
        }
        if (cookingTime <= 0) {
            throw new IllegalArgumentException("Cooking time must be a positive whole number.");
        }
        if (arrivalTime < 0) {
            throw new IllegalArgumentException("Arrival time cannot be negative.");
        }
        this.customer = customer;
        this.foodOrder = foodOrder;
        this.cookingTime = cookingTime;
        this.arrivalTime = arrivalTime;
        this.cookingTimeLeft = cookingTime;
    }

    public String getCustomer() {
        return customer;
    }

    public String getFoodOrder() {
        return foodOrder;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getCookingTimeLeft() {
        return cookingTimeLeft;
    }

    public void setCustomer(String customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer name cannot be null.");
        }
        this.customer = customer;
    }

    public void setFoodOrder(String foodOrder) {
        if (foodOrder == null) {
            throw new IllegalArgumentException("Food order cannot be null.");
        }
        this.foodOrder = foodOrder;
    }

    public void setCookingTime(int cookingTime) {
        if (cookingTime <= 0) {
            throw new IllegalArgumentException("Cooking time must be a positive whole number.");
        }
        this.cookingTime = cookingTime;
        this.cookingTimeLeft = cookingTime;
    }

    public void setArrivalTime(int arrivalTime) {
        if (arrivalTime < 0) {
            throw new IllegalArgumentException("Arrival time cannot be negative.");
        }
        this.arrivalTime = arrivalTime;
    }

    public void setCookingTimeLeft(int cookingTimeLeft) {
        if (cookingTimeLeft < 0) {
            throw new IllegalArgumentException("Cooking time left cannot be negative.");
        }
        this.cookingTimeLeft = cookingTimeLeft;
    }

    @Override
    public String toString() {
        return "Order{" +
               "customer='" + customer + '\'' +
               ", foodOrder='" + foodOrder + '\'' +
               ", cookingTime=" + cookingTime +
               ", arrivalTime=" + arrivalTime +
               ", cookingTimeLeft=" + cookingTimeLeft +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return cookingTime == order.cookingTime && arrivalTime == order.arrivalTime && cookingTimeLeft == order.cookingTimeLeft && java.util.Objects.equals(customer, order.customer) && java.util.Objects.equals(foodOrder, order.foodOrder);
    }

    @Override
    public int compareTo(Order otherOrder) {
        int arrivalComparison = Integer.compare(this.arrivalTime, otherOrder.arrivalTime);
        if (arrivalComparison != 0) {
            return arrivalComparison;
        }
        int cookingTimeComparison = Integer.compare(this.cookingTime, otherOrder.cookingTime);
        if (cookingTimeComparison != 0) {
            return cookingTimeComparison;
        }
        return this.customer.compareTo(otherOrder.customer);
    }
}