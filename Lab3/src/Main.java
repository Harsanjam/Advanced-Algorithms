import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            // Read in the number of vertices and edges
            int n = scanner.nextInt();
            int e = scanner.nextInt();

            // Build the adjacency list for the graph
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }

            // Read in the edges and add them to the adjacency list
            System.out.println("Enter the two vertices of the cable");
            for (int i = 0; i < e; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                adjList.get(u).add(v);
            }

            // Check if the graph is strongly connected
            if (isStronglyConnected(adjList)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    // Check if the graph is strongly connected using Kosaraju's algorithm
    private static boolean isStronglyConnected(List<List<Integer>> adjList) {
        int n = adjList.size();
        boolean[] visited = new boolean[n];
        // Do a DFS traversal of the graph
        dfs(adjList, 0, visited);
        // If any vertex is not visited, return false
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        // Reverse the graph
        List<List<Integer>> reversedAdjList = reverseGraph(adjList);
        // Mark all vertices as not visited
        Arrays.fill(visited, false);
        // Do a DFS traversal of the reversed graph
        dfs(reversedAdjList, 0, visited);
        // If any vertex is not visited, return false
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        // If all vertices are visited, return true
        return true;
    }
    // Perform a DFS traversal of the graph
    private static void dfs(List<List<Integer>> adjList, int u, boolean[] visited) {
        visited[u] = true;
        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                dfs(adjList, v, visited);
            }
        }
    }

    // Reverse the graph by swapping the direction of each edge
    private static List<List<Integer>> reverseGraph(List<List<Integer>> adjList) {
        //gets the size of the input adjacency list.
        int n = adjList.size();
        //initializes an empty list that will hold the reversed adjacency list.
        List<List<Integer>> reversedAdjList = new ArrayList<>();
        //initializes each list of the reversed adjacency list to an empty list.
        for (int i = 0; i < n; i++) {
            reversedAdjList.add(new ArrayList<>());
        }
        //iterates over each vertex u in the input graph.
        for (int u = 0; u < n; u++) {
            //iterates over each neighbor v of u.
            for (int v : adjList.get(u)) {
                //adds u to the list of neighbors for v in the reversed adjacency list.
                reversedAdjList.get(v).add(u);
            }
        }
        //returns the reversed adjacency list.
        return reversedAdjList;
    }
}
