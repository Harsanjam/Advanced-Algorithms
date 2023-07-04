import java.util.*;
public class Graph {
    List<List<Integer>> graph;
    boolean[] visited;
    boolean[] dfsVisited;
    Graph(int nodes){
        graph = new ArrayList<>();
        visited = new boolean[nodes];
        dfsVisited = new boolean[nodes];
        for(int i = 0; i < nodes; i++) {
            graph.add(i, new ArrayList<>());
        }
    }
    void addEdge(int source, int destination){
        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }
    void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            int node = queue.poll();
            System.out.print(node + " ");
            List<Integer> source = graph.get(node);
            for(int destination: source){
                if(!visited[destination]){
                    queue.add(destination);
                    visited[destination] = true;
                }
            }
        }
    }
    void dfs(){
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        dfsVisited[0] = true;

        while(!stack.isEmpty()){
            int node = stack.pop();
            System.out.print(node + " ");

            List<Integer> neighboursList = graph.get(node);
            for(int neighbour: neighboursList){
                if(!dfsVisited[neighbour]){
                    stack.push(neighbour);
                    dfsVisited[neighbour] = true;
                }
            }
        }
    }
    int degreeVertex(int node){
        return graph.get(node).size();
    }
    void printAdjVertices(int a){
        System.out.print(graph.get(a));
    }
}