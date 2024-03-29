// Learn JS 

import java.io.*;
import java.util.*;

class TestClass {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        

        int n = Integer.parseInt(br.readLine());
        int[] members = new int[n];
        for (int i = 0; i < n; i++){
            members[i] = Integer.parseInt(br.readLine());
        }

        int e = Integer.parseInt(br.readLine());
        int[][] ff = new int[e][];
        for(int i = 0; i < e; i++){
            ff[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>() {{
            for(int i = 0; i < n; i++){
                putIfAbsent(members[i], i);
            }
        }};

        int src = hmap.get(A);
        int dest = hmap.get(B);
        Graph g = new Graph(n);
        for (int[] edge: ff){
            int u = hmap.get(edge[0]);
            int v = hmap.get(edge[1]);
            g.addEdge(u, v);
        }
        bw.write((g.DFS(src, dest) ? 1 : 0) + "\n");
        bw.flush();
    }
}


class Graph {
    private int V;
    private List<Integer>[] adj;
    @SuppressWarnings("unchecked")
    Graph(int V) {
        this.V = V;
        this.adj = new List[V];
        for(int i = 0; i < V; i++){
            this.adj[i] = new LinkedList<>();
        }
    }
    // method to add connection in the graph
    public void addEdge(int src, int dest) {
        this.adj[src].add(dest);
    }
    // method to find whether there exist a path between src and destination
    public boolean DFS(int src, int dest){
        if(src == dest) return true;
        boolean[] vis = new boolean[V];
        vis[src] = true;

        for (int neigh : this.adj[src]){
            if (!vis[neigh] && dfsUtil(neigh, dest , vis)) {
                return true;
            }
        }
        return false;
    }
    private boolean dfsUtil(int src, int dest , boolean[] vis) {
        if (src == dest) return true;
        vis[src] = true;

        for (int neigh : this.adj[src]) {
            if (!vis[neigh] && dfsUtil(neigh, dest, vis)) {
                return true;
            }
        }
        return false;
    }
}