package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState {
    private static final int BLANK = 0;
    private int[][] cowmoo;
    private int N;
    private int[][] goal;
    public Board(int[][] tiles) {
        N = tiles.length;
        cowmoo = new int[N][N];
        goal = new int[N][N];
        int goalNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cowmoo[i][j] = tiles[i][j];
                goal[i][j] = goalNum;
                goalNum++;
            }
        }
        goal[N - 1][N - 1] = 0;

    }
    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) { // Corner case check
            throw new IndexOutOfBoundsException("Invalid index given: i == " + i + " j == " + j);
        }
        return cowmoo[i][j];
    }
    public int size() {
        return N;
    }
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming() {
        int ham = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cowmoo[i][j] != goal[i][j] && cowmoo[i][j] != BLANK) {
                    ham++;
                }
            }
        }

        return ham;
    }
    public int manhattan() {
        int man = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cowmoo[i][j] != goal[i][j] && cowmoo[i][j] != BLANK) {
                    int i2 = (cowmoo[i][j] - 1) / N;
                    int j2 = (cowmoo[i][j] - 1) % N;
                    man += Math.abs(i - i2) + Math.abs(j - j2);
                }
            }
        }
        return man;
    }
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Board otherBoard = (Board) o;
        if (this.N != otherBoard.N){
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.cowmoo[i][j] != otherBoard.cowmoo[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum = (sum + cowmoo[i][j]) * 31;
            }
        }
        return sum;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = size();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
