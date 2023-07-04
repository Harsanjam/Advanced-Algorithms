import java.util.*;

public class GraphCycleDetection {
    private int numV; // number of vertices
    private List<List<Integer>> adjacencyList; // adjacency list of the graph
    private boolean[] visited; // array to keep track of visited vertices
    private boolean hasCycle; // flag to track if the graph has a cycle

    // constructor
    public GraphCycleDetection(int numV) {
        this.numV = numV;
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < numV; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        this.visited = new boolean[numV];
    }

    // method to add an edge to the graph
    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    // DFS algorithm to check if the graph has a cycle
    private void dfs(int u, int parent) {
        visited[u] = true;
        for (int v : adjacencyList.get(u)) {
            if (!visited[v]) {
                dfs(v, u);
            } else if (v != parent) {
                // if v is already visited and it is not the parent of u, then there is a cycle
                hasCycle = true;
            }
        }
    }

    // method to check if the graph has a cycle
    public boolean hasCycle() {
        Arrays.fill(visited, false);
        hasCycle = false;
        for (int u = 0; u < numV; u++) {
            if (!visited[u]) {
                dfs(u, -1);
            }
        }
        return hasCycle;
    }

    // main method to test the algorithm
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices and edges:");
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int e = scanner.nextInt();
            GraphCycleDetection graph = new GraphCycleDetection(n);

            System.out.println("Enter the vertices connected by an edge:");
            for (int i = 0; i < e; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                graph.addEdge(u, v);
            }
            if (graph.hasCycle()) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        }
    }
}
