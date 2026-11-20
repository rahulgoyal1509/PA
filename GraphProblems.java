import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class GraphProblems {
    public static void dfs(int node, int[][] graph, boolean[] visited) {
        visited[node] = true;
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }

    public static void bfs(int start, int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    public static boolean validPath(int n, int[][] edges, int start, int end) {
        if (start == end) {
            return true;
        }
        List<Integer>[] graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : graph[node]) {
                if (next == end) {
                    return true;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return graph;
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    floodIsland(grid, r, c);
                }
            }
        }
        return count;
    }

    private static void floodIsland(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
                || grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '0';
        floodIsland(grid, r + 1, c);
        floodIsland(grid, r - 1, c);
        floodIsland(grid, r, c + 1);
        floodIsland(grid, r, c - 1);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    max = Math.max(max, area(grid, r, c));
                }
            }
        }
        return max;
    }

    private static int area(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
                || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 0;
        return 1 + area(grid, r + 1, c) + area(grid, r - 1, c)
                + area(grid, r, c + 1) + area(grid, r, c - 1);
    }

    public static void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O') {
                markSafe(board, r, 0);
            }
            if (board[r][cols - 1] == 'O') {
                markSafe(board, r, cols - 1);
            }
        }
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == 'O') {
                markSafe(board, 0, c);
            }
            if (board[rows - 1][c] == 'O') {
                markSafe(board, rows - 1, c);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'S') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private static void markSafe(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length
                || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'S';
        markSafe(board, r + 1, c);
        markSafe(board, r - 1, c);
        markSafe(board, r, c + 1);
        markSafe(board, r, c - 1);
    }

    public static int findCircleNum(int[][] isConnected) {
        int provinces = 0;
        boolean[] visited = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                provinces++;
                dfsProvince(isConnected, visited, i);
            }
        }
        return provinces;
    }

    private static void dfsProvince(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int next = 0; next < isConnected.length; next++) {
            if (isConnected[city][next] == 1 && !visited[next]) {
                dfsProvince(isConnected, visited, next);
            }
        }
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int room = queue.poll();
            visitedCount++;
            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.offer(key);
                }
            }
        }
        return visitedCount == rooms.size();
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int old = image[sr][sc];
        if (old != color) {
            fill(image, sr, sc, old, color);
        }
        return image;
    }

    private static void fill(int[][] image, int r, int c, int oldColor, int newColor) {
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length
                || image[r][c] != oldColor) {
            return;
        }
        image[r][c] = newColor;
        fill(image, r + 1, c, oldColor, newColor);
        fill(image, r - 1, c, oldColor, newColor);
        fill(image, r, c + 1, oldColor, newColor);
        fill(image, r, c - 1, oldColor, newColor);
    }

    public static void main(String[] args) {
        char[][] islands = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Islands: " + numIslands(islands));

        int[][] connection = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println("Provinces: " + findCircleNum(connection));

        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Valid path: " + validPath(3, edges, 0, 2));

        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList()
        );
        System.out.println("Can visit rooms: " + canVisitAllRooms(rooms));
    }
}
