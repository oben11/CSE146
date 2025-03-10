// Oliver Benjamin
// CSE146
// Lab05

// Manages processes and sorts them in our custom linked list queue..
public class ProcessScheduler {
    private LLQueue<Process> processes;
    private Process currentProcess;


    public ProcessScheduler()  {
        this.processes = new LLQueue<>();
        this.currentProcess = null;
     }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void addProcess(Process process) {
        if (currentProcess == null) {
            currentProcess = process;
         } else {
            processes.enqueue(process);
        }
    }

    public void runNextProcess() {
        currentProcess = processes.dequeue();
     }

    public void cancelCurrentProcess() {
        if (processes.peek() != null) {
        currentProcess = processes.dequeue();
        } else {
        currentProcess = null;
        }
    }

    public void printProcessQueue() {
        processes.print();
    }
}
