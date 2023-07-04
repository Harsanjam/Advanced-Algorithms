import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimMST {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            int n = input.nextInt();
            int e = input.nextInt();

            // Create adjacency list to store the graph
            ArrayList<ArrayList<Node>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            // Read in edges and weights
            for (int i = 0; i < e; i++) {
                int u = input.nextInt() - 1;
                int v = input.nextInt() - 1;
                int w = input.nextInt();
                graph.get(u).add(new Node(v, w));
                graph.get(v).add(new Node(u, w));
            }

            // Initialize visited array and priority queue
            boolean[] visited = new boolean[n];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0));

            // Initialize variables to store MST and weight
            ArrayList<Edge> mst = new ArrayList<>();
            int weight = 0;

            // Run Prim's algorithm
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int u = node.vertex;

                // Skip if already visited
                if (visited[u]) {
                    continue;
                }

                // Mark vertex as visited and add to MST
                visited[u] = true;
                if (node.prev != -1) {
                    mst.add(new Edge(node.prev, u, node.weight));
                    weight += node.weight;
                }

                // Add adjacent vertices to priority queue
                for (Node neighbor : graph.get(u)) {
                    if (!visited[neighbor.vertex]) {
                        pq.add(new Node(neighbor.vertex, neighbor.weight, u));
                    }
                }
            }

            // Print MST and weight
            System.out.println(weight);
            for (Edge edge : mst) {
                System.out.println((edge.u + 1) + " " + (edge.v + 1) + " " + edge.weight);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;
        int prev;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
            this.prev = -1;
        }

        Node(int vertex, int weight, int prev) {
            this.vertex = vertex;
            this.weight = weight;
            this.prev = prev;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }
    }

    static class Edge {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}