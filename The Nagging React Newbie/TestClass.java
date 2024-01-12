// Learn JS 

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        HashMap<Integer,Integer> revMap = new HashMap<Integer, Integer>(){{
            for(int i = 0; i < n; i++){
                putIfAbsent(i,members[i]);
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
        TreeSet<Integer> nodes = g.DFS(src, dest);

        if(nodes.isEmpty()) {
            bw.write("-1\n");
        }else {
            bw.write(nodes.stream().map(elem -> revMap.get(elem)).collect(Collectors.toList()).toString().replaceAll("\\[|]|, "," ") + "\n");
        }
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
    // method to find neigh. nodes of src that has a path to the destination
    public TreeSet<Integer> DFS(int src, int dest) {
        TreeSet<Integer> tset = new TreeSet<>();
        if(src == dest){
            tset.add(src);
        }
        boolean[] vis = new boolean[V];
        vis[src] = true;

        for(int neigh: this.adj[src]){
            if (neigh == dest) {
                tset.add(src);
            }
            else if (!vis[neigh]) {
                if (dfsUtil(neigh, dest, vis)) {
                    tset.add(neigh);        
                }
            }
        }
        return tset;
    }  
    private boolean dfsUtil(int src, int dest, boolean[] vis){
        vis[src] = true;
        if(src == dest)
            return true;
        
        for(int neigh : this.adj[src]) {
            if(!vis[neigh]) {
                if (dfsUtil(neigh, dest, vis)) {
                    return true;                   
                }
            }
        }
        return false;
    
    }
    
}