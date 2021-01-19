package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF map;
    private WeightedQuickUnionUF map2;
    private boolean[][] openMap;
    private int N;
    private int openSize = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        // 0 ~ N*N-1 map 2D-array, N*N is top, N*N+1 is bottom
        map = new WeightedQuickUnionUF(N * N + 2);
        // map2 do not have virtual bottom
        // 0 ~ N*N-1 map 2D-array, N*N is top
        map2 = new WeightedQuickUnionUF(N * N + 1);


        openMap = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                openMap[i][j] = false;
            }
        }
        this.N = N;

    }
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        int xyTo1D = xyTo1D(row, col);
        // if site has been open
        if (openMap[row][col]) {
            return;
        }
        // if site has not been open, turn it to open and connect with surrounding sites
        openMap[row][col] = true;
        openSize++;
        if (row == 0) {
            map.union(xyTo1D, N * N);  // union with top
            map2.union(xyTo1D, N * N);
        }
        if (row == N - 1) {
            map.union(xyTo1D, N * N + 1);   // union with bottom
        }
        if (row != 0 && openMap[row - 1][col]) {
            map.union(xyTo1D, xyTo1D(row - 1, col));
            map2.union(xyTo1D, xyTo1D(row - 1, col));
        }
        if (row != N - 1 && openMap[row + 1][col]) {
            map.union(xyTo1D, xyTo1D(row + 1, col));
            map2.union(xyTo1D, xyTo1D(row + 1, col));
        }
        if (col != 0 && openMap[row][col - 1]) {
            map.union(xyTo1D, xyTo1D(row, col - 1));
            map2.union(xyTo1D, xyTo1D(row, col - 1));
        }
        if (col != N - 1 && openMap[row][col + 1]) {
            map.union(xyTo1D, xyTo1D(row, col + 1));
            map2.union(xyTo1D, xyTo1D(row, col + 1));
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return openMap[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!openMap[row][col]) {
            return false;
        }
        int xyTo1D = xyTo1D(row, col);
        return map2.connected(xyTo1D, N * N);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }
    // does the system percolate?
    public boolean percolates() {
        return map.connected(N * N, N * N + 1);
    }
    public static void main(String[] args) {
        Percolation per = new Percolation(5);
        per.open(1, 2);
        per.open(1, 3);
        per.open(2, 3);
        per.open(3, 3);
        per.open(4, 3);
        per.open(0, 2);
        per.open(4, 1);
        System.out.println(per.numberOfOpenSites());
        System.out.println(per.isFull(4, 1));
        System.out.println(per.percolates());

    }

}
