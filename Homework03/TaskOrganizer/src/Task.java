// Oliver Benjamin
// CSE146
// Homework03
class Task {
    String action;
    int priority;

    public Task(String action, int priority) {
        this.action = action;
        this.priority = priority;
    }

    public Task() {
        this.action = "none";
        this.priority = 4;
    }

    @Override
    public String toString() {
        return "[Task] Priority: " + priority + " Task: " + action;
    }

}
