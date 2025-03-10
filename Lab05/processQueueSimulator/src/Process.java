// Oliver Benjamin
// CSE146
// Lab05

// Process object to be populated into the custom linked lists
public class Process {
    private String name;
    private double completionTime;

    // default constructor
    public Process() {
        this.name = "none";
        this.completionTime = 0.0;
    }

    // paramaterize constructor
    public Process(String name, double completionTime) {
        this.name = name;
        this.completionTime = completionTime;
    }


    // accessors for testing
    public String getName() {
        return name;
     }

    public void setName(String name) {
    if (name != null) {
            this.name = name;
         }
    }

    public double getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(double completionTime) {
        if (completionTime >= 0.0) {
            this.completionTime = completionTime;
        }
    }

    @Override
    public String toString() {
        return "Process Name: " + name + " Completion Time: " + completionTime;
    }
}