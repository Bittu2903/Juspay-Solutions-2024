import heapq

class Graph:
    def __init__(self, V):
        self.V = V
        self.adj = [[] for _ in range(V)]

    def add_edge(self, src, dest, wt):
        self.adj[src].append([dest, wt])

    def dijkstra(self, src, dest):
        vis = [False] * self.V
        dist = [float('inf')] * self.V
        dist[src] = 0
        pq = [(0, src)]

        while pq:
            wt, u = heapq.heappop(pq)

            if vis[u]:
                continue

            vis[u] = True

            for v, vWt in self.adj[u]:
                if dist[u] + vWt < dist[v]:
                    dist[v] = dist[u] + vWt
                    heapq.heappush(pq, (dist[v], v))

        return dist[dest] if dist[dest] != float('inf') else -1


if __name__ == "__main__":
    n = int(input())
    members = [int(input()) for _ in range(n)]

    e = int(input())
    ff = [list(map(int, input().split())) for _ in range(e)]

    A = int(input())
    B = int(input())

    hmap = {members[i]: i for i in range(n)}

    src = hmap[A]
    dest = hmap[B]
    g = Graph(n)

    for edge in ff:
        u, v, w = hmap[edge[0]], hmap[edge[1]], edge[2]
        g.add_edge(u, v, w)

    result = g.dijkstra(src, dest)
    print(result)
