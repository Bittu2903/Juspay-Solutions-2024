class Graph:
    def __init__(self, V):
        self.V = V
        self.adj = [[] for _ in range(V)]

    def add_edge(self, src, dest):
        self.adj[src].append(dest)

    def DFS(self, src, dest):
        tset = set()

        def dfs_util(src, dest, vis):
            vis[src] = True

            if src == dest:
                return True

            for neigh in self.adj[src]:
                if not vis[neigh]:
                    if dfs_util(neigh, dest, vis):
                        return True

            return False

        if src == dest:
            tset.add(src)

        vis = [False] * self.V
        vis[src] = True

        for neigh in self.adj[src]:
            if neigh == dest:
                tset.add(src)
            elif not vis[neigh]:
                if dfs_util(neigh, dest, vis):
                    tset.add(neigh)

        return sorted(tset)

if __name__ == "__main__":
    n = int(input())
    members = [int(input()) for _ in range(n)]

    e = int(input())
    ff = [list(map(int, input().split())) for _ in range(e)]

    A = int(input())
    B = int(input())

    hmap = {members[i]: i for i in range(n)}
    rev_map = {i: members[i] for i in range(n)}

    src = hmap[A]
    dest = hmap[B]

    g = Graph(n)

    for edge in ff:
        u, v = hmap[edge[0]], hmap[edge[1]]
        g.add_edge(u, v)

    nodes = g.DFS(src, dest)

    if not nodes:
        print("-1")
    else:
        print(" ".join(str(rev_map[node]) for node in nodes))
