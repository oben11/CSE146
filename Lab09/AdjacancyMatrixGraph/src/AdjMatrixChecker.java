/*
 * JJ
 */
public class AdjMatrixChecker {

	public static void main(String[] args) {
		testingPrintout("Making graph with 7 Vertices");
		AdjMatrixGraph graph = new AdjMatrixGraph(7);
		System.out.println("Graph is not null? "+(graph!=null));
		
		testingPrintout("Adding edges");
		
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 3, 1);
		
		graph.addEdge(1, 3, 1);
		
		graph.addEdge(2, 4, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 0, 1);
		
		graph.addEdge(3, 2, 1);
		graph.addEdge(3, 4, 1);
		
		graph.addEdge(4, 5, 1);
		graph.addEdge(4, 6, 1);
		
		System.out.println("10 Edges have been added.");

		testingPrintout("Print ADJMatrix");
		graph.printAdjMatrix();
		testingPrintout("Print DFS from Vertex 1 (index 0)");
		graph.printDFS();
		testingPrintout("Print BFS from Vertex 1 (index 0)");
		graph.printBFS();
		testingPrintout("Print DFS from Vertex 5 (index 4)");
		graph.printDFSFrom(4);
		testingPrintout("Printing BFS from Vertex 3 (index 2)");
		graph.printBFSFrom(2);
	}
	public static void testingPrintout(String msg)
	{
		System.out.println("-------------------------------------\nTEST: "+msg+"\n-------------------------------------");
	}
}