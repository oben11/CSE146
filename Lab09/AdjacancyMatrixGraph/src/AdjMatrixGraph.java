// Oliver Benjamin
// CSE 146
// Lab09
import java.util.LinkedList;
import java.util.Queue;

public class AdjMatrixGraph {
    private double[][] adjMatrix;
    private static final int DEF_VERT_COUNT = 10;

    // Default constructor for the AdjMatrixGraph with default vert count
    public AdjMatrixGraph() {
        this.adjMatrix = new double[DEF_VERT_COUNT][DEF_VERT_COUNT];
    }

    // paramaterize constructor with size vert count
    public AdjMatrixGraph(int size) {
        // Check valid size
        if (size > 0) {
            this.adjMatrix = new double[size][size];
        } else {
            // If invalid revert default
             this.adjMatrix = new double[DEF_VERT_COUNT][DEF_VERT_COUNT];
        }
    }

    // adds a new edge
    public void addEdge(int fromVertex, int toVertex, double weight) {
        // Check valid range
        if (fromVertex >= 0 && fromVertex < adjMatrix.length &&
            toVertex >= 0 && toVertex < adjMatrix.length) {
            this.adjMatrix[fromVertex][toVertex] = weight;
        } else {
            System.out.println("Invalid vertex index: " + fromVertex + " or " + toVertex);
        }
    }

    // print our matrix
    public void printAdjMatrix() {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 'depth first search' from the first vertex 0
    public void printDFS() {
        boolean[] visited = new boolean[adjMatrix.length];
        System.out.println("DFS from Vertex 0:");
        dfs(0, visited);
        System.out.println();
    }

    // 'depth first search' from startVertex 
    public void printDFSFrom(int startVertex) {
        if (startVertex >= 0 && startVertex < adjMatrix.length) {
            boolean[] visited = new boolean[adjMatrix.length];
            System.out.println("DFS from Vertex " + startVertex + ":");
            dfs(startVertex, visited);
            System.out.println();
        } else {
            System.out.println("Invalid starting vertex index.");
        }
    }

    // 'depth first search' logic
    private void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.println(vertex);

        // Explore neighbors in increasing order of their index
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[vertex][i] != 0 && !visited[i]) {
                dfs(i, visited);
            }
        }
    }

    // 'breadth first search' from the first vertex 0
    public void printBFS() {
        boolean[] visited = new boolean[adjMatrix.length];
        Queue<Integer> queue = new LinkedList<>();

        visited[0] = true;
        queue.offer(0);
        System.out.println("BFS from Vertex 0:");

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.println(currentVertex);

            // Explore neighbors in increasing order of their index
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[currentVertex][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
        System.out.println();
    }

    // 'breadth first search' from startVertex
    public void printBFSFrom(int startVertex) {
        if (startVertex >= 0 && startVertex < adjMatrix.length) {
            boolean[] visited = new boolean[adjMatrix.length];
            Queue<Integer> queue = new LinkedList<>();

            visited[startVertex] = true;
            queue.offer(startVertex);
            System.out.println("BFS from Vertex " + startVertex + ":");

            // Explore neighbors in increasing order of their index while the queue is not empty
            while (!queue.isEmpty()) {
                int currentVertex = queue.poll(); // current vertex equals current queue
                System.out.println(currentVertex);

                for (int i = 0; i < adjMatrix.length; i++) {
                    if (adjMatrix[currentVertex][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    }
                }
            }
            System.out.println();
        } else {
            System.out.println("Invalid starting vertex index.");
        }
    }
}