package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    MinPQ<Node> pq;
    private List<Node> solution;
    int moves;
    HashSet<Node> visited;
    private static class Node implements Comparable<Node>{
        WorldState worldState;
        // the number of moves made to reach this world state from the initial state.
        int moves;
        Node pre;
        public Node(WorldState worldState, Node pre, int moves) {
            this.worldState = worldState;
            this.moves = moves;
            this.pre = pre;
        }

        @Override
        public int compareTo(Node o) {
            int distance1 = this.worldState.estimatedDistanceToGoal() + this.moves;
            int distance2 = o.worldState.estimatedDistanceToGoal() +o.moves;
            return distance1 - distance2;
        }
    }


    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        solution = new ArrayList<>();
        moves = 0;
        visited = new HashSet<>();
        Node start = new Node(initial, null, 0);
        pq.insert(start);
        visited.add(start);
        searchHelper();
    }

    private void searchHelper() {
        Node delete = pq.delMin();
        moves = delete.moves;
        solution.add(delete);
        if (!delete.worldState.isGoal()) {
            insertNeighbors(delete);
            searchHelper();
        }
    }

    private void insertNeighbors(Node node) {
        for (WorldState w : node.worldState.neighbors()) {
            Node toAdd = new Node(w, node, moves + 1);
            if (!visited.contains(toAdd)) {
                visited.add(toAdd);
                pq.insert(toAdd);
            }
        }
    }


    public int moves() {
        return moves;
    }
    public Iterable<WorldState> solution() {
        List<WorldState> res = new ArrayList<>();
        for (Node node : solution) {
            res.add(node.worldState);
        }
        return res;
    }
}
