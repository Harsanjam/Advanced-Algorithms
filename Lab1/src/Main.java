import java.util.*;

public class Main {
    public static void main(String[] args) {
        int source, destination;
        int n, e, a;
        int counter = 1;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of vertices and edges:");
        n = scan.nextInt();
        e = scan.nextInt();

        Graph graph = new Graph(n);
        System.out.println("Enter the vertices connected by an edge:");
        while (counter <= e) {
            destination = scan.nextInt();
            source = scan.nextInt();
            graph.addEdge(source, destination);
            counter++;
        }

        System.out.println("\nBFS Output:");
        graph.bfs();
        System.out.println("\nDFS Output:");
        graph.dfs();

        System.out.println("\nEnter the vertex you wish to compute for: ");
        a = scan.nextInt();
        System.out.println("Degree of Vertex " + a + ": " + graph.degreeVertex(a));
        System.out.println("Adjacent vertices of " + a + ": ");
        graph.printAdjVertices(a);

    }
}
