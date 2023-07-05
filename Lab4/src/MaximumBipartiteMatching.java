import java.util.Scanner;
import java.util.*;

public class MaximumBipartiteMatching {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // number of test cases
        while (t-- > 0) {
            int m = sc.nextInt(); // number of applicants
            int n = sc.nextInt(); // number of jobs
            int[][] graph = new int[m+n+2][m+n+2]; // create a graph with source, sink and nodes for each applicant and job

            // add edges from source to applicants
            for (int i = 1; i <= m; i++) {
                graph[0][i] = 1;
            }

            // add edges from jobs to sink
            for (int i = 1; i <= n; i++) {
                graph[i+m][m+n+1] = 1;
            }

            // read input and add edges from applicants to jobs
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j+m] = sc.nextInt();
                }
            }

            int maxFlow = fordFulkerson(graph, 0, m+n+1); // find the maximum flow using Ford-Fulkerson algorithm
            System.out.println("The maximum number of applicants matching for the jobs is " + maxFlow);
        }
        sc.close();
    }

    // Ford-Fulkerson Algorithm to find maximum flow
    public static int fordFulkerson(int[][] graph, int source, int sink) {
        int[][] residualGraph = new int[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, residualGraph[i], 0, graph[i].length);
        }

        int maxFlow = 0;
        int[] parent = new int[graph.length];

        // finds augmenting paths from the source to the sink
        while (bfs(residualGraph, source, sink, parent)) {
            // calculates the bottleneck capacity of the path
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            // updates the residual graph
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            // updates the maximum flow
            maxFlow += pathFlow;
        }

        // returns the maximum flow from the source to the sink in the given graph
        return maxFlow;
    }


    // Breadth First Search to find augmenting path
    public static boolean bfs(int[][] graph, int source, int sink, int[] parent) {
        // this variable is to keep track of which nodes have been visited.  The visited array is initialized to false for each node

        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        // create a queue called queue and adds the source node source to it. marks the source node as visited by setting visited[source] to true

        queue.add(source);
        visited[source] = true;

        // initializing the parent array with -1 for each node in the graph, indicates that no path has been found yet
        parent[source] = -1;

        // a loop that runs while the queue is not empty
        // the loop continues until either the sink node sink is found (marked as visited),
        // or the queue is empty and there are no more nodes to visit.
        while (!queue.isEmpty()) {
            int u = queue.poll();

            // dequeues a node from the front of the queue and examines all its adjacent nodes
            for (int v = 0; v < graph.length; v++) {
                // If an adjacent node has not been visited yet and the residual
                // capacity of the edge between the current node and the adjacent node is greater than 0
                if (!visited[v] && graph[u][v] > 0) {
                    // mark the adjacent node as visited
                    visited[v] = true;

                    // set the parent of the adjacent node to the current node
                    parent[v] = u;

                    // and adds it to the back of the queue.
                    queue.add(v);
                }
            }
        }
        // returns true if the sink node sink was found, or false otherwise
        return visited[sink];
    }
}