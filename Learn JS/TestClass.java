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
        for(int i = 0; i < e; i ++){
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
            int w = edge[2];
            g.addEdge(u, v, w);
        }
        bw.write(g.Dij(src, dest) + "\n");
        bw.flush();
    }
}


class Graph {
    private int V;
    private ArrayList<ArrayList<LinkedList<Integer>>> adj;
    Graph(int V) {
        this.V = V;
        this.adj = new ArrayList<>();
        for(int i = 0; i < V; i++){
            this.adj.add(new ArrayList<LinkedList<Integer>>());
        }
    }
    // method to add connection in the graph
    public void addEdge(int src, int dest, int wt) {
        this.adj.get(src).add(new LinkedList<Integer>(){{
            add(dest);
            add(wt);
        }});
    }
    // method to return the shortest path to reach destination
    // we can use dijkstra algorithm
    public int Dij(int src, int dest) {
        boolean[] vis = new boolean[V];
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(vis, false);

        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int u = pq.peek()[0], wt = pq.peek()[1];
            pq.poll();

            if (vis[u]) {
                continue;                
            }
            vis[u] = true;
            ArrayList<LinkedList<Integer>> neigh = this.adj.get(u);
            for(LinkedList<Integer> node : neigh) {
                int v = node.get(0);
                int vWt = node.get(1);
                if (dist[u] + vWt < dist[v]) {
                    dist[v] = dist[u] + vWt;
                    pq.offer(new int[]{v,dist[v]});
                }
            }
        }

        if (dist[dest] == Integer.MAX_VALUE) {
            return -1;
        }
        else {
            return dist[dest];
        }
    }
}