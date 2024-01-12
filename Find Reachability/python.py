class Graph:
    def __init__(self, V):
        self.V = V
        self.adj = [[] for _ in range(V)]

    def add_edge(self, src, dest):
        self.adj[src].append(dest)

    def DFS(self, src, dest):
        if src == dest:
            return True

        vis = [False] * self.V
        vis[src] = True

        def dfs_util(src, dest, vis):
            if src == dest:
                return True

            vis[src] = True

            for neigh in self.adj[src]:
                if not vis[neigh] and dfs_util(neigh, dest, vis):
                    return True

            return False

        for neigh in self.adj[src]:
            if not vis[neigh] and dfs_util(neigh, dest, vis):
                return True

        return False


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
        u, v = hmap[edge[0]], hmap[edge[1]]
        g.add_edge(u, v)

    result = 1 if g.DFS(src, dest) else 0
    print(result)
