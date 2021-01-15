package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int N;
    private int T;
    private double[] result;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        this.result = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            int th = 1;
            for (; th <= N*N; th++) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);

                per.open(row, col);
                if (per.percolates()) {
                    break;
                }
            }
            result[i] = th /(double)(N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(this.T));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(this.T));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 200, new PercolationFactory());
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println("[" + ps.confidenceLow() + ", " + ps.confidenceHigh() + "]");
    }

}
